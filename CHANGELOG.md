## new

Fix:
* fix: Fix the stack overflow bug when Sequencex.flatten() is passed into TransformingSequence

New: 
* new: Added 'tools4j-resources' module and 'tools4j-run' module
* new: Sequencex add flatMapOfIterable, flatMapOfIterableTo, flatMapIndexed, flatMapIndexedOfIterable, flatMapIndexedOfIterableTo, flatMapIndexedTo, maxOf\* and minOf\* method
* new: Mapx added maxOf\* and minOf\* method
* new: Collectionx added emptyList, flatten, maxOf\* and minOf\*, flatMapIndexed, flatMapIndexedTo method
* new: Arrayx added flatMapIndexed and flatMapIndexedTo method
* new: Arrayx added maxOf, maxOfOrNull, maxOfWith, maxOfWithOrNull, minOf, minOfOrNull, minOfWith, minOfWithOrNull method

Change: 
* change: Sequencex.flattenSequenceOfIterable() rename to flattenOfIterable
* change: Sequencex max\* and min\* method rename to max\*OrNull and min\*OrNull
* change: Mapx max\* and min\* method rename to max\*OrNull and min\*OrNull
* change: Collectionx max\* and min\* method rename to max\*OrNull and min\*OrNull
* change: Arrayx max\* and min\* method rename to max\*OrNull and min\*OrNull

## v1.0.0-beta02
improve: Improve code
upgrade: Kotlin upgraded to 1.4.0
improve: Improve ktx library dependency

## v1.0.0-beta01
Initial release