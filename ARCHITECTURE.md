###                    Clothing App Framework Application Architecture
```
                    +---------------------------------------------+
                    |                                             |
                    |  pkg presentation                           |
                    |                                             |
                    +---------------------------------------------+
                    |                                             |
                    |            MainActivity                     |
Presentation Layer  |                  +                          |
                    |                  v                          |
                    |  pkg Home  HomeFragment+---->HomeViewModel  |
                    |                  +                          |
                    |                  v                          |
                    |  pkg Shop  ShopFragment+---->ProductViewer  |
                    |                  +                          |
                    |                  v                          |
                    |  pkg Cart  CartFragment+---->CartViewer     |
                    |                  +                          |
                    |                  v                          |
                    |pkg Checkout CheckoutFragment--->Checkoutpage|               
                    |                                             |
                    +-----------+---------------------------------+
                                |
                                |
                    +-----------v---------------------------------+
                    |                                             |
                    |     pkg business                            |
   Business Layer   |                                             |
                    |     QueryProducts                           |
                    |     Validity                                |
                    |                                             |
                    +---------------------------------------------+
                    |                                             |
                    |     pkg Application                         |
                    |                                             | 
                    |     DBController->Initizlized               |
                    |                                             |
                    +-----------+---------------------------------+
                                |
                                |
                                |
                                |
                                |
                    +-----------v---------------------------------+
                    |                                             |
                    |     pkg objects                             |
                    |                                             |
                    |     Product                                 |
                    |     Variant                                 |
                    |     Cart                                    |
                    |     CartItem                                |
       Data Layer   +---------------------------------------------+
                    |                                             |
                    |    pkg persistence                          |
                    |                                             |
                    |    DBInterface+>Implemented by+>DBModel     |
                    |    DBController->Implemented                |
                    |    DBMock->Constructed                      |
                    |    DBObject->Implentaion of DBInterface     |
                    |                                             |
                    +---------------------------------------------+
```
