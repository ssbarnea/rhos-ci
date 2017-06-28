#!/usr/bin/groovy

String call(String script) {

    script = '''#!/bin/bash
    # wrapper for limiting stdout output from commands
    set -eo pipefail

    function next_logfile() {
      n=
      set -C
      until
        file=$1${n:+-$n}.log
        [[ ! -f "$file" ]]
      do
        ((n++))
      done
      printf "$file"

    }

    log=$(next_logfile output)
    echo "[sh]: full log in ${BUILD_URL}artifact/$log"
    ( ''' + script + ''' ) 2>&1 | tee $log | awk -v offset=${MAX_LINES:-200} '{ if (NR <= offset) print; else { a[NR] = $0; delete a[NR-offset] } } END { for (i=NR-offset+1; i<=NR; i++) { if (!match(a[i],"^[[:space:]]*$")) print a[i]} }'
    '''
    sh script
}
