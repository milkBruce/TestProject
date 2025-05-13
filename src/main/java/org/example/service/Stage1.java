package org.example.service;

import org.example.Main;

import java.util.Stack;

public class Stage1 {
    private static class CharCount {
        char character;
        int count;

        CharCount(char character, int count) {
            this.character = character;
            this.count = count;
        }
    }
    public static String removeConsecutiveChars(String s) {
        String current = s;
        boolean changed = true;

        while (changed) {
            changed = false;
            Stack<CharCount> stack = new Stack<>();

            for (char c : current.toCharArray()) {
                if (!stack.isEmpty() && stack.peek().character == c) {
                    stack.peek().count++;
                } else {
                    // 处理栈中已有的需要删除的连续字符
                    while (!stack.isEmpty() && stack.peek().count >= 3) {
                        stack.pop();
                        changed = true;
                    }
                    stack.push(new CharCount(c, 1));
                }
            }

            // 处理栈中剩余的字符
            while (!stack.isEmpty() && stack.peek().count >= 3) {
                stack.pop();
                changed = true;
            }

            StringBuilder next = new StringBuilder();
            for (CharCount cc : stack) {
                next.append(String.valueOf(cc.character).repeat(cc.count));
            }
            current = next.toString();
        }

        return current;
    }
}
