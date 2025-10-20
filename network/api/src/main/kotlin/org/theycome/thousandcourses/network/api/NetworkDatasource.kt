package org.theycome.thousandcourses.network.api

/**
 * Created by Ivan Yakushev on 20.10.2025
 */
interface NetworkDatasource {
    suspend fun getCourses()
}
