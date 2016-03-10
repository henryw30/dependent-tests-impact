source ./config.sh

#!/bin/bash

function runPrioritization() {
    rm -rf ${priorDir}
    mkdir ${priorDir}

    orders=(absolute relative)
    ./prioritization-runner.sh
    java -cp ${impactJarCP} edu.washington.cs.dt.impact.Main.ResultsParser -directory ${priorDir}
}

function runSelection() {
    rm -rf ${seleDir}
    mkdir ${seleDir}

    orders=(absolute relative original)
    ./selection-runner.sh
    java -cp ${impactJarCP} edu.washington.cs.dt.impact.Main.ResultsParser -directory ${seleDir}
}

function runParallelization() {
    rm -rf ${paraDir}
    mkdir ${paraDir}

    ./paralleization-runner.sh
    java -cp ${impactJarCP} edu.washington.cs.dt.impact.Main.ResultsParser -directory ${paraDir}
}

echo "Running random-runner script"
./random-runner.sh

echo "Running prioritization-runner script"
runPrioritization

echo "Running selection-runner script"

echo "Running paralleization-runner script"
