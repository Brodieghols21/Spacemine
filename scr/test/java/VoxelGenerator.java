import java.util.ArrayList;
import java.util.Scanner;

public class VoxelGenerator {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<int[]> inputs = new ArrayList<int[]>();
        String givinp = "";
        while (1==1) {


            givinp = s.nextLine();
            if(givinp.equals("-1")||!(givinp.length()>=10))
                break;
            String[] inpints = givinp.split(",");
            int[] ints = new int[6];

            for (int i = 0; i < 6; i++) {
                ints[i] = Integer.parseInt(inpints[i]);
            }

            inputs.add(ints);
        }


        System.out.println("public static final VoxelShape shape = VoxelShapes.or(");
        for(int i=0;i<inputs.size(); i++) {
            int[] poss = inputs.get(i);
            System.out.print("            VoxelShapes.create(");
            for(int j=0;j<3;j++)
                System.out.print(poss[j]/16.0+", ");
            for(int j=0;j<2;j++)
                System.out.print((poss[j]+poss[j+3])/16.0+", ");
            System.out.print((poss[2]+poss[5])/16.0);
            if(i<inputs.size()-1)
                System.out.print("),\n");
            else
                System.out.print(")\n );");
        }

    }
}
