package com.igaworks.util.bolts_task;

import java.util.concurrent.CancellationException;

public class TaskUtils {
    public static <T> T wait(Task<T> task) {
        Throwable error;
        try {
            task.waitForCompletion();
            if (task.isFaulted()) {
                error = task.getError();
                if (error instanceof RuntimeException) {
                    throw ((RuntimeException) error);
                }
                throw new RuntimeException(error);
            } else if (!task.isCancelled()) {
                return task.getResult();
            } else {
                throw new RuntimeException(new CancellationException());
            }
        } catch (Throwable error2) {
            throw new RuntimeException(error2);
        }
    }
}
