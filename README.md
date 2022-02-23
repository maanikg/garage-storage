# garage-storage
Car Dealership Management System

Important Info:
I was unable to effectively complete my program - I ended up completed roughly 70% of the overall requirements I was planning on completing at the start. 
While I initially planned on starting with the core components and moving on to graphics, in actuality I started the program by implementing the core graphics and then moving on to the functionalities. Each sub screen served a different function, and thus had a different layout; as a result, a lot of time was spent creating the graphics part of the program. As a result, I was unable to implement various requirements such as an Instructions page, about menu, Comparable/Comparator interfaces, and 4 fully functional classes.

Bugs:
- when in the 'sell car panel' and changing manufacturer, the resolution of the car and tire images will get worse. Click on the car and tire buttons to make them clearer again
- sometimes, clicking on manufacturer/engine/tire/brake buttons will result in an undesired updating of statistics. I am not sure why this happens

Missing Functionalities:
- all implementation of money(profitcostmargin class is not used, budget panel is not implemented, shop panel is visible but has no effect on program)
- garage panel(both views) are not implemented
- inventory does not accurately represent number of car parts in storage because of lack of money implementation(ie. the program does not store the buying/selling of cars/car parts)
- next car/previous car buttons don't work(they don't do anything)
- the sold cars are unable to be viewed anywhere. I tried to have them appear in the middle of the main screen, but that would mess up the sell car panel(ie. the components would simply move over, not copy). I tried to find a work around, but by that time, it was too late.
- Instructions/About menu
- Implementation of sorting. I was planning on implementing sorting into the garage panel, but due to a lack of time was unable to implement both the panel and the sorting. I got stuck on trying to enable clicks on tables while disabling the editing of the table contents themselves, which is why the shop screen 'buy' buttons also don't do anything
- 4 classes: although I technically have 5 classes in the program, the profitcostmargin class, which was supposed to calculate the prices of all car parts and the overall car, was unable to be implemented into the program. I have a tablerenderer class that simply overrides a method that affects the characteristics of table input, but it does not do anything else related to the program.
