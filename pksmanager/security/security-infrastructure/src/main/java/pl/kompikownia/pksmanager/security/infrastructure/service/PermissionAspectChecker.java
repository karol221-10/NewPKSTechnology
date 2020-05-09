package pl.kompikownia.pksmanager.security.infrastructure.service;

import lombok.val;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.kompikownia.pksmanager.security.business.exception.InsufficientPermissionException;
import pl.kompikownia.pksmanager.security.business.internal.api.annotation.RequiresPermission;
import pl.kompikownia.pksmanager.security.infrastructure.model.UserDetailsModel;

import java.lang.reflect.Method;

@Aspect
@Component
public class PermissionAspectChecker {

    @Before("@within(pl.kompikownia.pksmanager.security.business.internal.api.annotation.RequiresPermission) ||" +
            "@annotation(pl.kompikownia.pksmanager.security.business.internal.api.annotation.RequiresPermission)")
    public void checkPermission(JoinPoint call) throws Throwable {
        val permissionName = getPermissionName(call);
        val actualPermissionList = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        val user = (UserDetailsModel)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean checkResult = actualPermissionList.stream().anyMatch(name -> permissionName.equals(name.getAuthority()));
        if(!checkResult) {
            throw new InsufficientPermissionException("User " + user.getUsername() + " has not permission "+permissionName);
        }
    }

    private String getPermissionName(JoinPoint call) {
        MethodSignature signature = (MethodSignature) call.getSignature();
        Method method = signature.getMethod();
        RequiresPermission annotation = method.getAnnotation(RequiresPermission.class);
        return annotation.value();
    }
}
