public class DataManipulation {
    public static void main(String[] args) {
        if (data.length % 2 == 0){
            median = (data[data.length / 2] + data[data.length / 2 - 1]) / 2.0;
            } else {
                median = data[data.length / 2];
                }
                System.out.println("Median: " + median);

                double variance = 0;
                for (int value : data){
                    variance += Math.pow((value - mean), 2);
                }
                double stdDev = Math.sqrt(variance / data.length);
                System.out.println("écart-type:  + stdDev");

                System.out.println("Min: " + data[0]);
                System.out.println("Max: " + data[data.length - 1]);
        }
        
    }

