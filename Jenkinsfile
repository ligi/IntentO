def flavorCombination='Prod'

node {

 stage 'checkout'
 checkout scm

 stage 'UITest'
 lock('adb') {
   try {
    sh "./gradlew clean spoon${flavorCombination}"
   } catch(err) {
    currentBuild.result = FAILURE
   } finally {
     publishHTML(target:[allowMissing: true, alwaysLinkToLastBuild: true, keepAll: true, reportDir: "android/build/spoon", reportFiles: '*/debug/index.html', reportName: 'Spoon'])
     step([$class: 'JUnitResultArchiver', testResults: 'android/build/spoon/*/debug/junit-reports/*.xml'])
   }
 }

 stage 'lint'
    try {
     sh "./gradlew clean lint${flavorCombination}Release"
    } catch(err) {
     currentBuild.result = FAILURE
    } finally {
     publishHTML(target:[allowMissing: true, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'android/build/outputs/', reportFiles: "lint-results-*Release.html", reportName: 'Lint'])
    }
    
 stage 'test'
   try {
    sh "./gradlew clean test${flavorCombination}DebugUnitTest"
   } catch(err) {
    currentBuild.result = FAILURE
   } finally {
    publishHTML(target:[allowMissing: true, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'android/build/reports/tests/', reportFiles: "*/index.html", reportName: 'UnitTest'])
    step([$class: 'JUnitResultArchiver', testResults: 'android/build/test-results/*/*.xml'])
   }


 stage 'assemble'
  sh "./gradlew clean assemble${flavorCombination}Release"
  archive 'android/build/outputs/apk/*'
  archive 'android/build/outputs/mapping/*/release/mapping.txt'
}