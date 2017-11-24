//5-4.9
public static int example1(int[] arr){
	int n = arr.length, total = 0;
	for(int j = 0; j < n; j++){
		total += arr[j];
	}
	return total;
}

//5-4.10
public static int example2(int[] arr){
	int n = arr.length, total = 0;
	for(int j = 0; j < n; j += 2){
		total += arr[j];
	}
	return total;
}

//5-4.10
public static int example3(int[] arr){
	int n = arr.length, total = 0;
	for(int j = 0; j < n; j++){
		for(int k = 0; k <= j; k++){
		total += arr[j];
		}
	}
	return total;
}

//5-4.11
public static int example4(int[] arr){
	int n = arr.length, prefix = 0; total = 0;
	for(int j = 0; j < n; j++){
		prefix += arr[j]
		total += prefix;
	}
	return total;
}

//5-4.12
public static int example1(int[] first, int[] second){
	int n = first.length, count = 0;
	for(int i = 0; i < n; i++){
		int total = 0;
		for(int j = 0; j < n; j++){
			for(int k = 0; k <= j; k++){
				total += first[k];
			}
		}
		if(second[i] == total){
			count++
		}
	}
	return count;
}