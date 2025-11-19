package org.theycome.thousandcourses.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.theycome.thousandcourses.presentation.ui.navigation.CoursesAccountKey
import org.theycome.thousandcourses.presentation.ui.navigation.CoursesFavoritesKey
import org.theycome.thousandcourses.presentation.ui.navigation.CoursesKey
import org.theycome.thousandcourses.presentation.ui.navigation.CoursesMainKey

/**
 * Created by Ivan Yakushev on 18.11.2025
 */
@Composable
fun TryScreen02(coursesKey: CoursesKey) {
    Column {
        if (coursesKey.value is CoursesMainKey) {
            Text("CoursesMainKey")
        } else if (coursesKey.value is CoursesFavoritesKey) {
            Text("CoursesFavoritesKey")
        } else if (coursesKey.value is CoursesAccountKey) {
            Text("CoursesAccountKey")
        }
    }
}

@Composable
fun TryScreen01CrashOnWhenToCompose(coursesKey: CoursesKey) {
    Column {
        when (coursesKey.value) {
            CoursesMainKey -> {
                println("CoursesMainKey")
            } // Text("CoursesMainKey")
            CoursesFavoritesKey -> {
                println("CoursesFavoritesKey")
            } // Text("CoursesFavoritesKey")
            CoursesAccountKey -> {
                println("CoursesAccountKey")
            } // Text("CoursesAccountKey")
        }
        Text("bla")
    }
}

// @Composable
// fun CoursesScreen(
//    coursesKey: CoursesKey,
//    modifier: Modifier = Modifier,
// ) {
//    Column(modifier) {
//        when (coursesKey.value) {
//            CoursesMainKey -> CoursesMainScreen(modifier)
//            CoursesFavoritesKey -> CoursesFavoritesScreen(modifier)
//            CoursesAccountKey -> CoursesAccountScreen(modifier)
//        }
//        NavigationBar(modifier)
//    }
