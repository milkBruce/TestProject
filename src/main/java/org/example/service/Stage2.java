package org.example.service;

import java.util.Stack;

public class Stage2 {
    private static class CharCount {
        char character;
        int count;

        CharCount(char character, int count) {
            this.character = character;
            this.count = count;
        }
    }

    public static String process(String s) {
        String current = s;
        boolean changed;

        do {
            String prev = current;

            current = processSinglePass(current);

            changed = !current.equals(prev);

        } while (changed);

        return current;
    }

    private static String processSinglePass(String s) {
        // 快速返回短字符串
        if (s.length() < 3) return s;

        Stack<CharCount> stack = new Stack<>();

        for (int i = 0; i < s.length(); ) {
            char currentChar = s.charAt(i);
            int count = 1;

            // 计算连续字符的数量
            while (i + count < s.length() && s.charAt(i + count) == currentChar) {
                count++;
            }

            i += count;

            // 处理连续字符
            if (count >= 3) {
                if (currentChar != 'a') {
                    // 替换非'a'字符为前驱字符
                    char replacement = getPreviousChar(currentChar);

                    // 尝试与栈顶合并
                    if (!stack.isEmpty() && stack.peek().character == replacement) {
                        stack.peek().count += 1; // 无论多少个连续字符，只替换为1个
                    } else {
                        stack.push(new CharCount(replacement, 1));
                    }
                }
                // 如果是'a'就直接跳过（相当于删除）
            } else {
                // 不足3个的字符直接加入
                if (!stack.isEmpty() && stack.peek().character == currentChar) {
                    stack.peek().count += count;
                } else {
                    stack.push(new CharCount(currentChar, count));
                }
            }
        }

        return buildStringFromStack(stack);
    }

    private static String buildStringFromStack(Stack<CharCount> stack) {
        StringBuilder sb = new StringBuilder();
        for (CharCount cc : stack) {
            sb.append(String.valueOf(cc.character).repeat(cc.count));
        }
        return sb.toString();
    }

    private static char getPreviousChar(char c) {
        return (char) ((c - 'a' - 1 + 26) % 26 + 'a');
    }

}
