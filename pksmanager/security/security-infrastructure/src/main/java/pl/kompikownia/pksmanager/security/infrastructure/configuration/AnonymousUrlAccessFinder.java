package pl.kompikownia.pksmanager.security.infrastructure.configuration;

import lombok.val;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.web.bind.annotation.*;
import pl.kompikownia.pksmanager.security.business.internal.api.annotation.AnonymousAccess;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public class AnonymousUrlAccessFinder {

    public static List<String> scanForAnonymousAccessEndpoints() {
        val scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(RestController.class));
        val beanDefinitions = scanner.findCandidateComponents("pl.kompikownia.pksmanager");
        return beanDefinitions.stream()
                .map(bean -> {
                    try {
                        return getBeanUrls(bean);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        return new ArrayList<String>();
                    }
                })
                .filter(not(List::isEmpty))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private static List<String> getBeanUrls(BeanDefinition bean) throws ClassNotFoundException {
        val className = Class.forName(bean.getBeanClassName());
        val annotatedMethods = Arrays.stream(className.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(AnonymousAccess.class))
                .collect(Collectors.toList());
        val globalAnnotation = className.getAnnotation(RestController.class);
        val url = globalAnnotation.value();
        return annotatedMethods.stream()
                .map(AnonymousUrlAccessFinder::getMappingValue)
                .filter(not(Optional::isEmpty))
                .map(Optional::get)
                .distinct()
                .map(string -> url + string)
                .collect(Collectors.toList());
    }

    private static Optional<String> getMappingValue(Method method) {
        if(method.isAnnotationPresent(GetMapping.class)) {
            GetMapping getMappingAnnotation = method.getAnnotation(GetMapping.class);
            return Optional.of( getMappingAnnotation.value()[0]);
        }

        if(method.isAnnotationPresent(PostMapping.class)) {
            PostMapping postMappingAnnotation = method.getAnnotation(PostMapping.class);
            return Optional.of(postMappingAnnotation.value()[0]);
        }

        if(method.isAnnotationPresent(DeleteMapping.class)) {
            DeleteMapping deleteMappingAnnotation = method.getAnnotation(DeleteMapping.class);
            return Optional.of(deleteMappingAnnotation.value()[0]);
        }

        if(method.isAnnotationPresent(PutMapping.class)) {
            PutMapping putMappingAnnotation = method.getAnnotation(PutMapping.class);
            return Optional.of(putMappingAnnotation.value()[0]);
        }
        return Optional.empty();
    }
}
