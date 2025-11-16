#include <stdio.h>

int main() {
    char *str = "Hello world";
    printf("AND with 127: ");
    for (int i = 0; str[i] != '\0'; i++) {
        char res = str[i] & 127;
        printf("%c", res);
    }
    printf("\n");

    printf("XOR with 127: ");
    for (int i = 0; str[i] != '\0'; i++) {
        char res = str[i] ^ 127;
        printf("%c", res);
    }
    printf("\n");

    return 0;
}
