# 이 프로젝트에서만 JAVA_HOME을 임시로 고정해서 gradlew를 실행하는 PowerShell 버전.
# 세션 전체(시스템/사용자 환경변수)의 JAVA_HOME은 건드리지 않고,
# 이 스크립트 프로세스 안에서만 값을 바꿉니다.
#
# 사용법: .\gradlew.local.ps1 test
$env:JAVA_HOME = "C:\Program Files\Java\jdk-17"
$env:Path = "$env:JAVA_HOME\bin;" + $env:Path
& "$PSScriptRoot\gradlew.bat" @args
exit $LASTEXITCODE
