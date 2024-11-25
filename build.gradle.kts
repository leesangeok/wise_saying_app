//plugins {
//    id("java")
//}
//
//group = "com.ll"
//version = "1.0-SNAPSHOT"
//
//repositories {
//    mavenCentral()
//}
//
//dependencies {
//    testImplementation(platform("org.junit:junit-bom:5.10.0"))
//    testImplementation("org.junit.jupiter:junit-jupiter")
//    implementation 'com.google.code.gson:gson:2.9.0'
//
//}
//
//tasks.test {
//    useJUnitPlatform()
//}
plugins {
    id("java")
}

group = "com.ll"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral() // Maven Central 저장소
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.google.code.gson:gson:2.9.0") // Kotlin DSL에 맞는 문법으로 수정
}

tasks.test {
    useJUnitPlatform()
}
