#include <cstdio>

int main() {
	//freopen("sample.txt", "r", stdin);
	int cnt = 0;

	for (int i = 0; i < 9; i++) {
		char arr[9] = { 0, };
		for (int j = 0; j < 9; j++) {
			scanf("%c", &arr[j]);

			if (arr[j] == 'F') {
				if ((i % 2 == 0) && (j % 2 == 0))
					cnt++;
				if ((i % 2 != 0) && (j % 2 != 0))
					cnt++;
			}
		}
	}

	printf("%d", cnt);
	return 0;
}