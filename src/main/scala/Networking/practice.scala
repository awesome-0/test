//package Networking
//
//object practice extends App{
//
//
////  trait GrandFather
////  object firstSon extends GrandFather
////  case object secondSon extends GrandFather
////
////  trait greet[+T]{
////    def greeting
////  }
////
////
////  trait Cart[+T]{
////    def add[U >:T](item : U ) : U
////    def delete[U>:T](item : U) : U
////  }
//
////  sealed trait shop
////  sealed trait Items extends shop
////
////  sealed trait Groceries extends Items
////  sealed trait Electronics extends Items
////  case object pay extends shop
////  case object food extends Items
////
////  case object tv extends Electronics
////
////  case object Food
////  case object Fruits extends Cart[Electronics]{
////    override def add[U >: Electronics](item: U): U =
////
////    override def delete[U >: Electronics](item: U): U = ???
////  }
////
////  class Fruit
////  class Apple extends Fruit
////  class Mangoes extends Fruit
////
////  case object cart extends Cart[Apple]{
////
////  }
//
//
//  trait thing
//  trait vehicle extends thing
//  class car extends vehicle
//  class jeep extends car
//  class camry extends car
//  class bike extends vehicle
//  class bicycle extends vehicle
//  class tricycle extends bicycle
//  class vegetable extends thing
//  class smallcar extends car
//
//
//  class parking[A >: jeep <: vehicle] (val place : A)
//
//  val carparking = new parking[vegetable](new vegetable)
//
//
//
//
//
//
//}
