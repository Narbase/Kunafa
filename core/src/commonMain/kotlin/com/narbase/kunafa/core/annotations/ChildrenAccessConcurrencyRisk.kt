package com.narbase.kunafa.core.annotations

/*
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2017] -[2019] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 2023/12/14.
 */


@Retention(value = AnnotationRetention.BINARY)
@RequiresOptIn(
    level = RequiresOptIn.Level.WARNING,
    message = "Iterating through children might lead to concurrency issues. Use with caution"
)
public annotation class ChildrenAccessConcurrencyRisk