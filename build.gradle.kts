// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
        val compose_ui_version by extra("1.6.6")
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
}