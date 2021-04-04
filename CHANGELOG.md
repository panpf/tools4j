## v1.0.0-rc02

### tools4j-collections

Arrayx:
* change: Arrayx now needs to return an empty list, now all use Collectionx.emptyList()
* improve: Arrayx's joinTo() method no longer throws IOException
* improve: remove the use of @SafeVarargs annotation

Collectionx:
* fix: Fix Collectionx's chunked method bug
* change: Collectionx now needs to return an empty list, now all use Collectionx.emptyList()
* change: Collectionx's joinTo() method now throws IllegalStateException
* improve: remove the use of @SafeVarargs annotation
* new: Collectionx added requireNoNulls(), unzip(), emptySet(), list(), windowed() method

Mapx:
* change: Mapx now needs to return an empty list, now all use Collectionx.emptyList()
* improve: remove the use of @SafeVarargs annotation
* new: Mapx added emptyMap() method

### tools4j-date
* fix: Fix the bug that Datex.formatTimeLength(1590L, "%ms?ms") should return '1590ms' instead of '1ms'
* fix: Fix the bug that Datex.formatTimeLength(90L, "%MS?ms") should return '090ms' instead of '90ms'
* fix: Fix the bug that Datex.formatTimeLength(0L, "%d?d %h?h %m?m %s?s %ms?ms") should return '0ms' instead of '0s'
* deprecated: Datex's formatTimeLength() method now mark @Deprecated，please use formatDuration() method
* new: Datex adds formatDuration() method

### tools4j-io
* new: Filex adds testFilterFileNameIllegalCharacters(), testFilterFileNameIllegalCharactersOrNull() method

### tools4j-lang
* deprecated: String?.orEmpty() method now mark @Deprecated
* deprecated: Stringx`s max(), maxWith(), maxBy(), min(), minWith(), minBy() method now mark @Deprecated
* new: Stringx added maxOrNull(), maxWithOrNull(), maxByOrNull(), minOrNull(), minWithOrNull(), minByOrNull() method

### tools4j-sequences
* deprecated: Sequencex's sum() method now mark @Deprecated, please use sumOfLong() method
* change: Sequencex's joinTo() method now throws IllegalStateException
* improve: Most of Sequencex methods now accept nullable Sequence
* new: Sequencex added sumOfLong(), chunked(), windowed() method

### tools4j-test
* new: Assertx added assertTwoNoThrow() method


## v1.0.0-rc01

### Other
* upgrade: Kotlin upgraded to 1.4.10
* downgrade: In order to be compatible with android 4.1, sourceCompatibility and targetCompatibility are reduced to 1.6


## v1.0.0-beta03

### tools-collections

Arrayx:
* remove: Arrayx remove asList() method
* rename: Arrayx's max\*(), min\*() method rename to max\*OrNull(), min\*OrNull()
* change: Arrayx2 merged to Arrayx
* new: Arrayx added flatMapIndexed(), flatMapIndexedTo(), maxOf(), maxOfOrNull(), maxOfWith(), maxOfWithOrNull(), minOf(), minOfOrNull(), minOfWith(), minOfWithOrNull() method

Collections:
* rename: Collectionx's max\*(), min\*() method rename to max\*OrNull(), min\*OrNull()
* new: Collectionx added emptyList(), flatten(), maxOf\*(), minOf\*(), flatMapIndexed(), flatMapIndexedTo(), withIndex(), onEach(), onEachIndexed(), asList() method

Mapx:
* rename: Mapx's max\*(), min\*() method rename to max\*OrNull(), min\*OrNull()
* new: Mapx added maxOf\*(), minOf\*() method

Groupingx:
* merge: Groupingx merge to 'tools4j-collections' module


### tools-sequences
* fix: Fix the stack overflow bug when Sequencex.flatten() is passed into TransformingSequence
* rename: Sequencex's flattenSequenceOfIterable() methed rename to flattenOfIterable()
* rename: Sequencex's max\*(), min\*() method rename to max\*OrNull(), min\*OrNull()
* improve: Sequencex.map\*() method can now pass in null
* new: Sequencex added flatMapOfIterable(), flatMapOfIterableTo(), flatMapIndexed(), flatMapIndexedOfIterable(), flatMapIndexedOfIterableTo(), flatMapIndexedTo(), maxOf\*(), minOf\*(), onEachIndexed() method

### Module
* rename: 'tools4j-run rename to 'tools4j-environment'
* new: Added 'tools4j-resources' module
* new: Added 'tools4j-run' module
* new: Added 'tools4j-test' module
* new: Added 'tools4j-test-ktx' module


## v1.0.0-beta02

### Other
* improve: Improve code
* improve: Improve ktx library dependency
* upgrade: Kotlin upgraded to 1.4.0


## v1.0.0-beta01
Initial release