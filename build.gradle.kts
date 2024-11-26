////plugins {
////    id("java")
////}
////
////group = "com.ll"
////version = "1.0-SNAPSHOT"
////
////repositories {
////    mavenCentral()
////}
////
////dependencies {
////    testImplementation(platform("org.junit:junit-bom:5.10.0"))
////    testImplementation("org.junit.jupiter:junit-jupiter")
////    implementation 'com.google.code.gson:gson:2.9.0'
////
////}
////
////tasks.test {
////    useJUnitPlatform()
////}
//plugins {
//    id("java")
//}
//
//group = "com.ll"
//version = "1.0-SNAPSHOT"
//
//repositories {
//    mavenCentral() // Maven Central 저장소
//}
//
//dependencies {
//    testImplementation(platform("org.junit:junit-bom:5.10.0"))
//    testImplementation("org.junit.jupiter:junit-jupiter")
//    implementation("com.google.code.gson:gson:2.9.0") // Kotlin DSL에 맞는 문법으로 수정
//}
//
//tasks.test {
//    useJUnitPlatform()
//}
plugins {
    java
}

group = "com.ll"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral() // Maven Central 저장소
}

dependencies {
    // JUnit 5 BOM을 사용하여 버전 관리
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // Gson 라이브러리
    implementation("com.google.code.gson:gson:2.9.0")

    // Assertions 라이브러리 (선택 사항)
    testImplementation("org.assertj:assertj-core:3.23.1")
}

tasks.test {
    useJUnitPlatform()
}
