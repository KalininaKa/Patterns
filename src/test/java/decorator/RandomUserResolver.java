package decorator;

import models.UserModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.reflect.Parameter;

public class RandomUserResolver  implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return parameterContext.isAnnotated(RandomUser.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return generateUser(parameterContext.getParameter());
    }

    private Object generateUser(Parameter parameter) {
        Class<?> type = parameter.getType();
        if (UserModel.class.equals(type)) {
            return UserModel.builder()
                    .name("user_" + RandomStringUtils.randomNumeric(3))
                    .job(RandomStringUtils.randomAlphabetic(10))
                    .build();
        }
        throw new ParameterResolutionException("No random user is generated for " + type);
    }
}