package com.insightfullogic.java8.examples.chapter4;

import java.util.function.Supplier;

public class Logger {

    private boolean debug = true;

    public boolean isDebugEnabled() {
        return debug;
    }

    public void debug(String message) {
        if (isDebugEnabled()) {
            System.out.println(message);
        }
    }

    // 例4-1　使用isDebugEnabled 方法降低日志性能开销
    public void example() {
        // BEGIN debug_optimised
		Logger logger = new Logger();
		if (logger.isDebugEnabled()) {
		    logger.debug("Look at this: " + expensiveOperation());
		}
        // END debug_optimised
    }

    private String expensiveOperation() {
        return "";
    }

    // 例4-3　启用Lambda 表达式实现的日志记录器
    // BEGIN debug_lambda
    public void debug(Supplier<String> message) {
        if (isDebugEnabled()) {
            debug(message.get());
        }
    }
    // END debug_lambda

    // 例4-2　使用Lambda 表达式简化日志代码
    public void exampleWithLambda() {
        // BEGIN debug_optimised_lambda
		Logger logger = new Logger();
		logger.debug(() -> "Look at this: " + expensiveOperation());
        // END debug_optimised_lambda
    }

}
