package hr.kraljic.spel.textparser2.parser.config;

import org.springframework.expression.*;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class CustomPropertyAccessor<T> implements PropertyAccessor {
    private final Class<T> clazz;
    private final TypeResolver<T> typeResolver;
    private final ExpressionParser expressionParser;

    public CustomPropertyAccessor(Class<T> clazz, TypeResolver<T> typeResolver) {
        this.clazz = clazz;
        this.typeResolver = typeResolver;
        this.expressionParser = new SpelExpressionParser();
    }

    @Override
    public Class<?>[] getSpecificTargetClasses() {
        return null;
    }

    @Override
    public boolean canRead(EvaluationContext evaluationContext, Object o, String s) {
        Expression expression = expressionParser.parseExpression(s);
        Object value = expression.getValue(o);
        if (value == null) {
            return false;
        }
        return this.clazz == value.getClass();
    }

    @Override
    public TypedValue read(EvaluationContext evaluationContext, Object o, String s) {
        Expression expression = expressionParser.parseExpression(s);
        T value = expression.getValue(o, clazz);
        String strVal = typeResolver.resolve(value);

        return new TypedValue(strVal);
    }

    @Override
    public boolean canWrite(EvaluationContext evaluationContext, Object o, String s) {
        return false;
    }

    @Override
    public void write(EvaluationContext evaluationContext, Object o, String s, Object o1) {
        throw new UnsupportedOperationException();
    }
}
