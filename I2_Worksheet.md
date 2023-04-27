Iteration 2 Worksheet
=====================

Paying off technical debt
--------------------------

One way we eliminated technical debt in this iteration was by adding a database object class to make allow easier access when querying database objects. We are now able to grab a single object instead of an entire table. This makes the job easier for the front end, as there is no need to search through the list of products returned after making a query. This technical debt was deliberate. As time was running out in the first iteration, our original method was implemented as that was all we needed to achieve optimal state at that time. As our needs grew in size, we fixed the rushed implementation.
[Adding Database](https://code.cs.umanitoba.ca/3350-winter-2021-a03/group8/-/commit/436ecd46cd39b081056df3a6fdcb34a410f31f6a)

Another thing we did was moved the product validity code to its file in the business layer. It takes the responsibility away from the data layer to better follow the single responsibility technique. It is an example of accidental technical debt. It wasn't intentionally done however, we realized later that the functionality would be better suited in the business layer.
[Shifting Product Validity Code](https://code.cs.umanitoba.ca/3350-winter-2021-a03/group8/-/commit/394db901775b9fe6408ba3235b9976b459f870f3)



SOLID
-----

There is string formatting logic happening for the time in the presentation layer (Both MainActivity.java and StatisticsActivity.java) that could be done in the logic layer. This violates the Single Responsibility Principle.

[Solid Violation](https://code.cs.umanitoba.ca/3350-winter-2021-a01/group-8-overfeed-the-world/-/issues/22)

Retrospective
-------------

Due to the amount of time it took to develop features in the first iteration, we have decided to downsize the number of features. We had to remove the fast purchase feature since it is more convenient and doesn't limit the functionality of the program. Additionally, we also decided to discard the user creation and associated.



Design patterns
----------------

We used the adapter design pattern here:
[Link to Design Pattern](https://code.cs.umanitoba.ca/3350-winter-2021-a03/group8/-/blob/dev/app/src/main/java/com/example/clothingappframework/presentation/cart/CartViewer.java)

We used CartViewer as an adapter for RecyclerView to allow us to use a view that better suits our needs.
[Link to Cart Viewer](https://sourcemaking.com/design_patterns/adapter)

Iteration 1 Feedback fixes
---------------------------

https://code.cs.umanitoba.ca/3350-winter-2021-a03/group8/-/issues/41

After the first iteration, we had a problem with our file structure. During the second iteration, we had fixed this by moving MainActivity.java to the presentation layer.
[File Structure](https://code.cs.umanitoba.ca/3350-winter-2021-a03/group8/-/commit/bf121414b78e084443523009ab52dfa021adb169)


