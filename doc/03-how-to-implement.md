03 | How to Implement
=====================

I confess! I don't know how to make a calculator. I could google it
and start building, but that would basically be copy + paste.

The truth is I don't know **exactly** how to build a calculator, but
I can think about the basic structure 💭

---

In fact, we need to build a functional calculator. It consists in:

1. Getting math prompt (probably a String), such as: `1 + 2 * 3`
2. Turn it into math/digit values: `(int) 1 | (Operator) + | (int) 2
   | (Operator) * | (int) 3`
3. Evaluate math sequence to a math result: `1 + (2 * 3) => 1 + 6
   => 7`

Our obstacles
-------------

Easy to implement, right? But let's take a look at our obstacles:

1. How can I print all this logical stuff in a pretty way at
   terminal?
2. How can I implement error printing at terminal? What's the
   pretties way?
3. How can I respect the math laws preserving readability?
4. Can I build this with some programming principle
   (OOP, Modularity, ...)?
5. How much easy-to-use can I make it?
6. _More questions will likely arise as development progresses._
