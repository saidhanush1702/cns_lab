#include <stdio.h>

int main() {
    char str[] = "Hello world";
    for (int i = 0; str[i] != '\0'; i++) {
        char res = str[i] ^ 0;
        printf("%c", res);
    }
    printf("\n");
    return 0;
}
