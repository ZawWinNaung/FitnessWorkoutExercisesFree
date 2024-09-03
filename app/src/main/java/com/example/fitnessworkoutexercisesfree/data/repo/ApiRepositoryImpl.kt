package com.example.fitnessworkoutexercisesfree.data.repo

import com.example.fitnessworkoutexercisesfree.data.ApiService
import com.example.fitnessworkoutexercisesfree.domain.model.Exercise
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ApiRepository {
    override suspend fun getExercises(): Result<List<Exercise>> {
        return try {
            val response = apiService.getExercises()
            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyList())
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}