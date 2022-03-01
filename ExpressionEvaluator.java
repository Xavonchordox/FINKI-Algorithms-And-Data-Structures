package Test8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ExpressionEvaluator {

    public static int evaluateExpression(String expression){
        int n = expression.length();
        Stack<Integer> stack = new Stack<>();
        Stack<String> sign = new Stack<>();
        String[] text = expression.split("");
        int number = 0;

        for(int i=0; i<n-1; i++){//10+100
            if(Character.isDigit(expression.charAt(i))){
                number += expression.charAt(i) - '0';
                if(Character.isDigit(expression.charAt(i+1))){
                    number *= 10;
                }
                else{
                    stack.push(number);
                    number = 0;
                }
            }
        }
        number += expression.charAt(n-1) - '0';
        stack.push(number);

        for(int i=0; i<n; i++){
            String next = text[i];
            if(next.equals("+") || next.equals("*")){
                sign.push(next);
            }
        }

        while (!sign.isEmpty()){
            String s = sign.pop();
            int a = stack.pop();
            int b = stack.pop();

            if(sign.size() > 0){
                if(s.equals("+") && sign.peek().equals("*")) {
                    int c = stack.pop();
                    stack.push(b * c);
                    stack.push(a);

                    sign.pop();
                    sign.push(s);
                }

                else if(s.equals("+")){
                    stack.push(a+b);
                }
                else if(s.equals("*")){
                    stack.push(a*b);
                }
            }
            else if(s.equals("+")){
                stack.push(a+b);
            }
            else if(s.equals("*")){
                stack.push(a*b);
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression(input.readLine()));
    }

}