## new
* improve: remove the use of @SafeVarargs annotation
* deprecated: Deprecated String?.orEmpty() method
* improve: Most of Sequencex methods now accept nullable Sequence
* new: Collectionx added requireNoNulls(), unzip() method
* new: Stringx added maxOrNull, maxWithOrNull, maxByOrNull, minOrNull, minWithOrNull, minByOrNull method
* deprecated: Deprecated Stringx`s max, maxWith, maxBy, min, minWith, minBy method
* new: Sequencex added sumOfLong method
* deprecated: Deprecated Sequencex sum method
* new: Collectionx added emptySet method, Mapx added emptyMap method
* new: Assertx added assertTwoNoThrow method
* improve: Arrayx's joinTo() method no longer throws IOException
* change: Collectionx's joinTo() method now throws IllegalStateException
* change: Sequencex's joinTo() method now throws IllegalStateException
* new: Collectionx added list, windowed method


## v1.0.0-rc01

Other:
* upgrade: Kotlin upgraded to 1.4.10
* downgrade: In order to be compatible with android 4.1, sourceCompatibility and targetCompatibility are reduced to 1.6


## v1.0.0-beta03

Fix:
* fix: Fix the stack overflow bug when Sequencex.flatten() is passed into TransformingSequence

New: 
* new: Added 'tools4j-resources' module and 'tools4j-run' module
* new: Sequencex add flatMapOfIterable, flatMapOfIterableTo, flatMapIndexed, flatMapIndexedOfIterable, flatMapIndexedOfIterableTo, flatMapIndexedTo, maxOf\* and minOf\*, onEachIndexed method
* new: Mapx added maxOf\* and minOf\* method
* new: Collectionx added emptyList, flatten, maxOf\* and minOf\*, flatMapIndexed, flatMapIndexedTo, withIndex, onEach, onEachIndexed method
* new: Arrayx added flatMapIndexed and flatMapIndexedTo method
* new: Arrayx added maxOf, maxOfOrNull, maxOfWith, maxOfWithOrNull, minOf, minOfOrNull, minOfWith, minOfWithOrNull method
* new: Collectionx added asList method
* new: Added 'tools4j-test' module and 'tools4j-test-ktx' module

Change: 
* change: Sequencex.flattenSequenceOfIterable() rename to flattenOfIterable
* change: Sequencex max\* and min\* method rename to max\*OrNull and min\*OrNull
* change: Mapx max\* and min\* method rename to max\*OrNull and min\*OrNull
* change: Collectionx max\* and min\* method rename to max\*OrNull and min\*OrNull
* change: Arrayx max\* and min\* method rename to max\*OrNull and min\*OrNull
* change: Arrayx2 merged to Arrayx
* change: Groupingx move to 'tools4j-collections' module
* change: 'tools4j-run rename to 'tools4j-environment'

Remove:
* remove: Arrayx remove asList method

Improve:
* improve: Sequencex.map\* method can now pass in null

## v1.0.0-beta02

Improve:
* improve: Improve code
* improve: Improve ktx library dependency

Other:
* upgrade: Kotlin upgraded to 1.4.0

## v1.0.0-beta01
Initial release