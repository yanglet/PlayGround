package com.example.concurrency.aop

import org.springframework.expression.EvaluationContext
import org.springframework.expression.ExpressionParser
import org.springframework.expression.spel.standard.SpelExpressionParser
import org.springframework.expression.spel.support.StandardEvaluationContext

class CustomSpELParser {
    companion object {
        fun getDynamicValue(parameterNames: Array<String?>, args: Array<Any?>, name: String): Any? {
            val parser: ExpressionParser = SpelExpressionParser()
            val context: EvaluationContext = StandardEvaluationContext()
            parameterNames.forEachIndexed { index, name ->
                if (!name.isNullOrEmpty()) {
                    context.setVariable(name, args[index])
                }
            }
            return parser.parseExpression(name).getValue(context)
        }
    }
}