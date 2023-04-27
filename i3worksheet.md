What technical debt has been cleaned up
========================================

https://code.cs.umanitoba.ca/3350-winter-2021-a03/group8/-/commit/f93507335f192a3a88b6308bb4eeb0fad071b12e

Our project had some architectural technical debt(Deliberate and Prudent) from the previous iteration.
Most of logic and the data storing were happened at the presentation layer. 
With this commit, we paid off the technical debt we had. 

What technical debt did you leave?
==================================

Some technical debt that we are leaving is a small bug in our code. 
We weren't able to figure out what was causing it and had some more important issues that needed to be addressed before the end of the iteration. 
The bug happens when hitting the back button from a product page. 
Upon returning to the shop screen, the user will see that all of the items in the shop array have been duplicated (If there were 2 items originally, there will be 4 after doing those steps. 
There will then be 6 if the same steps are repeated, and so on). 
This technical debt is deliberate. We are aware that this bug exists, however since it isn't app breaking, it didn't get moved up the priority chain soon enough.

Discuss a Feature or User Story that was cut/re-prioritized
============================================

https://code.cs.umanitoba.ca/3350-winter-2021-a03/group8/-/issues/8
The account creation feature was cut from development during the second iteration.
Due to an increase in backlog, we needed to choose a feature or two to remove. 
We chose this feature since it is non essential to the function of our app, it is more of an extra that would be nice to have. 
Along with this feature, we also chose to remove order history as this relied on the account creation feature to implement.

Acceptance test, untestable
===============

A difficulty we had while making tests was testing the square payment feature properly. 
It's was a tricky part to implement, and was implemented near the end of the iteration. 
Due to the complexity and the little time we had left, we never managed to get properly working tests for this feature.

Velocity/teamwork
=================

During this iteration, our time estimates got more generous. We overestimated the amount of work we would get done during the previous iteration and so this iteration, a lot of our time estimates ended up being more than the actual time spent developing. While this isn't more accurate estimating, it results in less disappointment from expectations that are too high.
Below are some examples of issues that have much a much higher expected time than actual development time:
https://code.cs.umanitoba.ca/3350-winter-2021-a03/group8/-/issues/55
https://code.cs.umanitoba.ca/3350-winter-2021-a03/group8/-/issues/51
https://code.cs.umanitoba.ca/3350-winter-2021-a03/group8/-/issues/42
