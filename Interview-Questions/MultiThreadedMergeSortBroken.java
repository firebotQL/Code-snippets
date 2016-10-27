import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadedMergeSortBroken {
    public static int THREAD_COUNT = 4;
    public static Integer[] arr = { 767, 119, 746, 678, 827, 278, 373, 67, 766, 289, 132, 628, 158, 189, 737, 720, 471, 388, 864, 424, 160, 8, 383, 418, 520, 686, 385, 413, 440, 758, 423, 352, 690, 318, 979, 367, 311, 952, 787, 89, 845, 1, 200, 692, 609, 66, 549, 221, 857, 435, 772, 873, 319, 920, 144, 611, 399, 670, 577, 488, 275, 455, 598, 627, 778, 233, 689, 363, 480, 570, 646, 145, 774, 695, 921, 946, 697, 700, 129, 285, 309, 194, 643, 906, 521, 429, 74, 667, 983, 276, 57, 843, 980, 939, 942, 763, 215, 644, 814, 434, 412, 210, 96, 263, 653, 639, 784, 608, 540, 277, 348, 60, 880, 405, 727, 715, 324, 303, 986, 545, 888, 860, 501, 989, 656, 65, 172, 818, 84, 19, 964, 922, 45, 923, 996, 739, 896, 819, 863, 937, 621, 125, 408, 606, 513, 343, 31, 187, 925, 99, 905, 642, 281, 425, 254, 514, 848, 15, 354, 674, 755, 705, 407, 914, 347, 82, 801, 447, 783, 257, 20, 308, 33, 581, 910, 28, 202, 835, 747, 349, 612, 290, 378, 615, 458, 502, 450, 687, 735, 410, 112, 978, 595, 556, 924, 46, 808, 867, 562, 369, 930, 605, 457, 976, 523, 317, 616, 341, 563, 397, 969, 130, 310, 593, 209, 3, 852, 485, 990, 931, 765, 148, 984, 898, 754, 85, 11, 460, 361, 622, 244, 131, 164, 63, 162, 868, 685, 468, 321, 50, 647, 484, 839, 519, 62, 463, 42, 775, 693, 702, 155, 965, 282, 743, 329, 159, 504, 381, 878, 620, 398, 261, 974, 701, 433, 207, 184, 237, 706, 532, 580, 39, 247, 235, 800, 960, 379, 90, 117, 241, 999, 826, 195, 105, 307, 236, 649, 325, 994, 469, 394, 376, 988, 355, 927, 25, 557, 168, 641, 411, 829, 239, 834, 438, 542, 232, 891, 640, 900, 993, 904, 537, 116, 464, 721, 1000, 486, 213, 575, 442, 10, 911, 127, 892, 987, 677, 903, 286, 113, 330, 736, 929, 748, 111, 738, 255, 776, 955, 107, 553, 871, 437, 389, 204, 358, 9, 652, 445, 390, 338, 492, 56, 789, 630, 683, 773, 510, 316, 714, 345, 658, 364, 624, 578, 916, 149, 218, 805, 657, 366, 161, 262, 741, 212, 250, 793, 967, 709, 393, 163, 473, 431, 506, 279, 785, 899, 34, 16, 675, 291, 752, 591, 661, 635, 265, 730, 351, 761, 744, 427, 548, 79, 496, 120, 587, 312, 959, 252, 908, 483, 306, 837, 226, 258, 981, 462, 59, 651, 604, 958, 669, 719, 92, 723, 583, 534, 823, 94, 293, 509, 18, 7, 353, 788, 672, 602, 387, 629, 569, 881, 682, 633, 733, 472, 861, 328, 283, 151, 465, 466, 507, 69, 38, 43, 985, 770, 336, 812, 756, 525, 320, 749, 634, 126, 887, 400, 271, 350, 165, 530, 198, 171, 782, 14, 88, 932, 304, 836, 691, 951, 114, 810, 326, 883, 503, 173, 498, 454, 23, 452, 724, 441, 970, 182, 822, 147, 637, 21, 404, 70, 284, 118, 584, 544, 78, 142, 696, 273, 824, 26, 238, 762, 29, 143, 753, 436, 497, 382, 662, 340, 679, 267, 764, 97, 377, 415, 211, 623, 150, 251, 516, 479, 439, 401, 422, 217, 745, 515, 110, 539, 37, 302, 517, 809, 339, 190, 961, 798, 592, 40, 179, 638, 478, 224, 853, 346, 536, 231, 287, 722, 876, 93, 482, 298, 940, 122, 626, 470, 138, 489, 300, 140, 24, 133, 68, 55, 108, 86, 208, 698, 77, 73, 396, 938, 185, 850, 58, 862, 357, 579, 372, 603, 552, 95, 666, 941, 313, 294, 375, 855, 288, 585, 494, 731, 109, 613, 897, 223, 870, 944, 176, 991, 571, 512, 268, 156, 332, 75, 681, 928, 841, 907, 342, 234, 632, 315, 177, 44, 174, 926, 874, 821, 370, 368, 491, 541, 123, 997, 459, 193, 631, 49, 248, 134, 856, 599, 266, 786, 101, 461, 688, 833, 882, 954, 219, 527, 327, 91, 768, 780, 270, 443, 792, 849, 966, 395, 323, 576, 32, 831, 206, 453, 797, 716, 157, 582, 146, 305, 448, 945, 791, 188, 334, 806, 665, 153, 771, 726, 663, 245, 935, 982, 30, 902, 610, 186, 561, 4, 103, 371, 467, 885, 825, 446, 476, 522, 586, 950, 183, 972, 851, 712, 913, 6, 227, 574, 167, 81, 391, 794, 477, 475, 356, 654, 335, 5, 713, 12, 790, 2, 949, 47, 256, 243, 909, 816, 72, 128, 331, 555, 292, 166, 703, 36, 535, 668, 933, 645, 710, 98, 847, 866, 214, 240, 589, 884, 828, 917, 676, 191, 977, 554, 61, 344, 533, 48, 272, 528, 197, 619, 432, 80, 895, 333, 859, 430, 416, 962, 104, 259, 365, 975, 680, 181, 495, 948, 865, 538, 893, 779, 314, 795, 487, 230, 64, 449, 838, 844, 192, 671, 742, 511, 565, 614, 815, 875, 573, 100, 359, 374, 728, 392, 83, 934, 71, 890, 178, 499, 973, 137, 409, 508, 115, 456, 740, 180, 444, 858, 953, 17, 102, 664, 684, 832, 600, 963, 725, 526, 225, 596, 781, 550, 141, 968, 500, 607, 830, 260, 846, 205, 572, 660, 27, 820, 269, 246, 220, 601, 428, 918, 419, 717, 426, 796, 877, 751, 912, 169, 840, 386, 384, 51, 901, 699, 625, 13, 481, 817, 76, 360, 152, 518, 618, 597, 22, 729, 490, 956, 567, 380, 280, 546, 559, 175, 274, 451, 106, 617, 417, 242, 957, 869, 813, 136, 531, 803, 760, 87, 297, 943, 998, 707, 551, 732, 894, 403, 590, 299, 636, 568, 558, 704, 802, 228, 919, 170, 711, 322, 139, 673, 971, 529, 264, 650, 406, 135, 414, 648, 124, 41, 854, 769, 879, 995, 804, 750, 547, 203, 121, 53, 493, 420, 196, 249, 337, 588, 474, 734, 295, 505, 543, 566, 799, 594, 560, 694, 52, 889, 201, 777, 886, 655, 154, 253, 718, 301, 362, 811, 35, 936, 708, 757, 759, 524, 842, 915, 807, 872, 947, 199, 229, 421, 296, 222, 216, 402, 54, 564, 992, 659 };
    public static Set<String> threads = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>());

    public static void main(String[] args) {
        //System.out.println("Unsorted array: " + Arrays.toString(arr));
        int defaultNumberOfThreads = 4;
        for(int i = 0; i < 1000; i++) {
            THREAD_COUNT = defaultNumberOfThreads;
            mergeSort(Arrays.copyOf(arr, 1000));
            System.out.println(threads.size());
            assert (threads.size() == (defaultNumberOfThreads + 1)); // 4 spawned threads + main thread
            threads.clear();
        }
       // System.out.println("Sorted array: " + Arrays.toString(arr));
       // System.out.println("Used threads: " + Arrays.toString(threads.toArray()));
    }

    public static Comparable[] mergeSort(Comparable[] list) {
        threads.add(Thread.currentThread().getName());

        if (list.length <= 1) {
            return list;
        }

        int mid = list.length / 2;

        Comparable[] leftList = Arrays.copyOfRange(list, 0, mid);
        Comparable[] rightList = Arrays.copyOfRange(list, mid, list.length);
        if (THREAD_COUNT == 0) {
            mergeSort(leftList);
            mergeSort(rightList);
        } else {
            THREAD_COUNT -= 1;
            Thread leftThread = createMergeSortThread(leftList);
            THREAD_COUNT -= 1;
            Thread rightThread = createMergeSortThread(rightList);

            leftThread.start();
            rightThread.start();

            try {
                leftThread.join();
                rightThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        merge(leftList, rightList, list);
        return list;
    }

    private static void merge(Comparable[] first, Comparable[] second, Comparable[] result) {
        int i = 0, j = 0, m = 0;

        while(i < first.length && j < second.length) {
            result[m++] = first[i].compareTo(second[j]) < 0 ?  first[i++] : second[j++];
        }
        System.arraycopy(first, i, result, m, first.length - i);
        System.arraycopy(second, j, result, m, second.length - j);
    }

    private static Thread createMergeSortThread(final Comparable[] list) {

        return new Thread() {
            @Override
            public void run() {
                mergeSort(list);
            }
        };
    }
}
