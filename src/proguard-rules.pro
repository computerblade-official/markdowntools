# Add any ProGuard configurations specific to this
# extension here.

-keep public class com.puterblade.markdowntools.Markdowntools {
    public *;
 }
-keeppackagenames gnu.kawa**, gnu.expr**

-optimizationpasses 4
-allowaccessmodification
-mergeinterfacesaggressively

-repackageclasses 'com/puterblade/markdowntools/repack'
-flattenpackagehierarchy
-dontpreverify
