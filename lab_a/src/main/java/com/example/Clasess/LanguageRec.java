// /* package com.example.Clasess;

// public class LanguageRec {

//     /* This class will let us identify the language in the input, to later replace all the reserved icons
//      * and then replace them  with valid symbols, that will not the program. 
//     */

//     private String regex;
//     private String nRegex;

//     public LanguageRec(String reg) {
//         this.regex = reg;
//         Checker();
//         nRegex = "";

//     }

//     private void Checker() {
//         //First we need to check the whole input, read and start to identy the items that will interfire with 
//         //the program

//         for(int i = 0; i < regex.length(); i++){
//             Character c = regex.charAt(i);
//             boolean val = Conversion.pMap.containsKey(c);
//             if(val){
//                 //Then the element is in the Map, and it'll be used as a normal symbol
//                 nRegex +=  c.toString().replace(c, '@');
//             }
//         }
//         System.out.println(regex);
//     }

//     @Override
//     public LanguageRec toString() {
//         // TODO Auto-generated method stub
//         return nRegex;
//     }
// }
 