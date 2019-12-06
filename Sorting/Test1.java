//package Sorting;
//
//import java.util.Arrays;
//
//public class Test1 {
//
//	
//	public void mergeSort(List<Patient> a, boolean isID) {
//        Patient[] helper = new Patient[a.size()];
//        sort(a, 0, a.size() - 1, helper, isID);
//    }
//
//    private void sort(List<Patient> a, int lo, int hi, Patient[] helper, boolean isID) {
//        if (lo >= hi) return;
//        int mid = lo + (hi - lo) / 2;
//        sort(a, lo, mid, helper, isID);
//        sort(a, mid + 1, hi, helper, isID);
//        merge(a, lo, mid, hi, helper, isID);
//    }
//
//    private void merge(List<Patient> a, int lo, int mid, int hi, Patient[] helper, boolean isID) {
//        if(isID){
//            for (int i = lo; i <= hi; i++) {
//                helper[i] = a.get(i);
//            }
//            int i = lo;
//            int j = mid + 1;
//            for (int k = lo; k <= hi; k++) {
//                if (i > mid) {
//                    a.set(k,helper[j++]);
//                } else if (j > hi) {
//                    a.set(k,helper[i++]);
//                } else if (helper[i].getHosRecordNum().compareTo(helper[j].getHosRecordNum()) <= 0) {
//                    a.set(k,helper[i++]);
//                } else {
//                    a.set(k,helper[j++]);
//                }
//            }
//        }
//        
//        else{
//            Comparator<Object> com= Collator.getInstance(java.util.Locale.CHINA);
//            for (int i = lo; i <= hi; i++) {
//                helper[i] = a.get(i);
//            }
//            int i = lo;
//            int j = mid + 1;
//            for (int k = lo; k <= hi; k++) {
//                if (i > mid) {
//                    a.set(k,helper[j++]);
//                } else if (j > hi) {
//                    a.set(k,helper[i++]);
//                } else if (com.compare(helper[i].getName(), helper[j].getName()) <= 0) {
//                    a.set(k,helper[i++]);
//                } else {
//                    a.set(k,helper[j++]);
//                }
//            }
//        }
//    }
//
//}
