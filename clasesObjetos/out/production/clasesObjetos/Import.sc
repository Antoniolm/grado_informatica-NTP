val d1=new java.util.Date

import java.util.Date

val d2=new Date

import java.util

val d3=new util.Date

import collection.mutable.{ArrayBuffer,Queue}

val obj1=new ArrayBuffer[String]
obj1+="Hola"

val obj2=new Queue[Int]
obj2.enqueue(3,4,5)
val x=obj2.dequeue()

def generarNumero():Int ={
  import java.util.Random

  val generador=new Random
  generador.nextInt()
}

import collection.mutable.{Map => MutMap}

val m1=Map(1->2)
val m2=MutMap(2->3)
