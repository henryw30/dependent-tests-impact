# To use this file, run 'source setup-vars.sh'

# Root directory for our tools and results
export DT_ROOT=/home/user/dependent-tests-impact/experiments

# Directory for all the jar files containing the DT-impact tools and its dependencies
export DT_TOOLS=$DT_ROOT/impact-tools/*

# Directory for libraries of the old subject
export DT_LIBS=$DT_SUBJ/dependency/*
# Compiled class files of the old subject
export DT_CLASS=$DT_SUBJ/classes
# Compiled automatically-generated test files of the old subject
export DT_RANDOOP=$DT_SUBJ/randoop/bin
# Compiled human-written test files of the old subject
export DT_TESTS=$DT_SUBJ/test-classes

# Directory for libraries of the new subject
export NEW_DT_LIBS=$NEW_DT_SUBJ/dependency/*
# Compiled class files of the new subject
export NEW_DT_CLASS=$NEW_DT_SUBJ/classes
# Compiled human-written test files of the new subject
export NEW_DT_TESTS=$NEW_DT_SUBJ/test-classes
# Compiled automatically-generated test files of the new subject
export NEW_DT_RANDOOP=$NEW_DT_SUBJ/randoop/bin

# Directory for all the jar files containing the DT-impact tools and its dependencies
export PRIO_RESULTS=$DT_ROOT/prioritization-results
export SELE_RESULTS=$DT_ROOT/selection-results
export PARA_RESULTS=$DT_ROOT/parallelization-results

# Directory for all the scripts used to generate the figures in our paper
export DT_SCRIPTS=$DT_ROOT/scripts
# Directory for all the hardcoded data related to our subjects
export DT_DATA=$DT_SCRIPTS/data

# Directory for all the jar files containing the DT-impact tools and its dependencies
export PRIO_DT_LISTS=$DT_DATA/prioritization-dt-list
export SELE_DT_LISTS=$DT_DATA/selection-dt-list
export PARA_DT_LISTS=$DT_DATA/parallelization-dt-list

# Files containing the minimum number of dependent tests a subject has
export AUTO_MIN_DTS=$DT_DATA/AUTO_MIN_DTs.txt
export ORIG_MIN_DTS=$DT_DATA/ORIG_MIN_DTs.txt 
