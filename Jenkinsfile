@Library('common') _
// import static common.Utils.*


node {

    stage('gitClean') {
        gitClean()
    }

    stage("mkdtemp") {
       def x = mkdtemp('cd-')
       println "${x}"
    }

    stage("md5") {
       def jobname = "${env.JOB_NAME}#${env.BUILD_NUMBER}"
       def x = md5(jobname, 6)
       println "md5('${jobname}', 6) => ${x}"
    }

    stage("sh2") {
        withEnv(["MAX_LINES=2"]) {
            sh2 "seq 5" // should display 1,2,4,5 (missing 3) and output.log
            sh2 "true" // should generate output-2.log
            archiveArtifacts artifacts: '*.log'
        }
    }

}
