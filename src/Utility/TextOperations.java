package Utility;

import Model.FogNode;

/**
 * @author Dhruv
 * @version 1.0
 *          Date 12/6/16
 *          <p>
 *          This is a implementation class for
 */
public class TextOperations {

    public void clean(String line) {

        if (line.split(" ")[0].toLowerCase().equals("fognode"))
            System.out.println("FogNode");
    }

    public FogNode clean_FogNode(String line) {
        FogNode fogNode1 = null;

        String[] values = line.split(" ");
        /**
         * Sample Request
         * FogNode Max_Response_Time t MY_IP MY_UDP MY_TCP N1 TCP1 N2 TCP2 ...
         * Hence,
         *      values[1] = Max_Response_Time
         *      values[2] = Queue_Time
         *      values[3] = Host_Name
         *      values[4] = UDP Port No
         *      values[5] = TCP Port No
         *      values[6] onwards neighbours...
         */
        if (values.length < 6) {
            fogNode1 = new FogNode();
            fogNode1.setInitializationMessage("Error. Values for fog Node Not Specified correctly");
            return fogNode1;
        } else {
            try {
                long Max_Response_Time = Long.parseLong(values[1]);
                long Queue_Time = Long.parseLong(values[2]);
                int UDP_No = Integer.parseInt(values[4]);
                int TCP_No = Integer.parseInt(values[5]);

                fogNode1 = new FogNode(Max_Response_Time, Queue_Time, values[3], UDP_No, TCP_No);
            } catch (NumberFormatException e) {
                fogNode1 = new FogNode();
                fogNode1.setInitializationMessage("Error. Values which should be Numbers are inncorrectly specified.");
                return fogNode1;
            }
            /**
             * NOW populate neighbours
             */
            // If no of values is greater than 6 or length is even then only neighbours present
            if (values.length > 6 && values.length % 2 == 0) {
                try {
                    int i = 6, j = 7;
                    while (i < values.length) {
                        /** Format : FogNode Add Neighbour to List -> (Hostname, TCP Port No) */
                        fogNode1.setList_of_neighbours(values[i], Integer.parseInt(values[j]));
                        System.out.println(values[i]+ Integer.parseInt(values[j]));
                        i+=2;
                        j+=2;
                    }

                    // Get size of the List of Neighbours, and set that as the No of neighbours for this fog node
                    fogNode1.setNo_of_neighbours(fogNode1.getList_of_neighbours().size());

                } catch (NumberFormatException e) {
                    fogNode1 = new FogNode();
                    fogNode1.setInitializationMessage("Error. TCP port No of the neighbour is not a proper number");
                    return fogNode1;
                }

            }

            if(fogNode1!=null)
                fogNode1.setInitializationMessage("Success");
            else
                fogNode1.setInitializationMessage("Failure");
            return fogNode1;
        }
    }
}
