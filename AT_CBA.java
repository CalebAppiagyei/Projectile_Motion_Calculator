
/////////////////////////////////////////////////////////////
//
// Name: Caleb Appiagyei
//
// Date: 3/9/21
//
// Program: Air Trajectory
//
// Description: This program will simulate the flight
// of a projectile, given parameters describing the 
// environment, projectile and launch conditions.
//
/////////////////////////////////////////////////////////////

// Import your classes
import java.util.*;

public class AT_CBA
{
   /**
    * @param args
    */
   public static void main(String[] args)
   {
   
   // Name your scanner class
      Scanner input = new Scanner (System.in);
   
   // Create your variables
      double pdiam, pmass, dcoeff, langle, lheight, lspeed, radians, vVelocity, hVelocity, dh, dv;
      char choice;
   
      System.out.println("<*><*><*> Program AirTrajectory <*><*><*>");
      System.out.println();
   
   // Prompt the user for the Projectile Parameters
      System.out.print("Enter Projectile Diameter (cm): ");
      pdiam = input.nextDouble();
      double cdiam = pdiam / 100;
      System.out.print("Enter Projectile Mass (g): ");
      pmass = input.nextDouble();
      double cmass = pmass / 1000;
      System.out.print("Enter Drag Coefficient: ");
      dcoeff = input.nextDouble();
      System.out.println();
   
   // Prompt the user for the Launch Parameters 
      System.out.print("Enter Launch Angle (degrees): ");
      langle = input.nextDouble();
      System.out.print("Enter Launch Height (cm): ");
      lheight = input.nextDouble();
      double cheight = lheight / 100; 
      System.out.print("Enter Launch Speed (m/s): ");
      lspeed = input.nextDouble();
      System.out.println();
   
      radians = langle * Math.PI / 180;
      hVelocity = lspeed * Math.cos(Math.toRadians(langle));
      vVelocity = lspeed * Math.sin(Math.toRadians(langle));
   
   // Ask the user if they would like to see the details
      System.out.print("Display details (y/n)? ");
      choice = input.next().charAt(0);
      System.out.println();
   
      System.out.println("3-2-1 ... Launch!");
      System.out.println();
   
   // Print the details if the user asks for them
      if( choice == 'y' ){
         System.out.println(" Time      X-Accel     Y-Accel     X-Vel      Y-Vel     X-Pos     Y-Pos");
         System.out.println("  (s)      (m/s^2)     (m/s^2)     (m/s)      (m/s)      (m)       (m)");
         System.out.println(" ------    -------     -------     -----      -----     -----     -----");
      }
      // Assign values to your variables
      double vpos = cheight;
      double hpos = 0;
      double area = Math.PI * Math.pow((cdiam/2), 2);
      double vaccel = 0, haccel = 0;   
      double apg = vpos;
      double apgT = 0.0;
      if ( choice == 'y' ){   
         System.out.printf("%7.4f %10.2f %10.2f %10.2f %10.2f %9.2f %9.2f",0.0, haccel, vaccel, hVelocity, vVelocity, hpos, vpos);
         System.out.println();
      }   
      
      double time = 0;
      double deltat = 0.1;
      for(double t = 0.1; vpos > 0; t = t + deltat ){
         dh = 0.5 * dcoeff * 1.162 * area * Math.pow(hVelocity, 2);
         dv = 0.5 * dcoeff * 1.162 * area * Math.pow(vVelocity, 2);
         
         // Calculate the acceleration 
         haccel = -dh / cmass;
         if( vVelocity > 0 ) {
            vaccel = ((-1 * dv) / cmass) - 9.800;
         }
         else {
            vaccel = (dv / cmass) - 9.800;
         }
         
         // Calculate the change in velocity
         hVelocity += (haccel * deltat);
         vVelocity += (vaccel * deltat);
             
         // Calculate the change in position
         hpos += (hVelocity * deltat);
         vpos += (vVelocity * deltat);
         
         if ( apg <= vpos ) {
            apg = vpos;
            apgT = t; 
         }
         
         // Print the results
         if ( choice == 'y' ) {
            System.out.printf("%7.4f %10.2f %10.2f %10.2f %10.2f %9.2f %9.2f",t, haccel, vaccel, hVelocity, vVelocity, hpos, vpos);
            System.out.println();   
         }
         time = t;
      }
   // Calculate the Impact Speed and angle   
      double ispd = Math.sqrt((Math.pow(hVelocity, 2) + Math.pow(vVelocity, 2)));
      double iang = Math.atan(vVelocity / hVelocity) * 180/Math.PI;
   
   // Print the Projectile Parameters
      System.out.println();
      System.out.println("Projectile Parameters ...");
      System.out.println("Diameter: " + pdiam + " cm");
      System.out.println("Net Mass: " + pmass + " g");
      System.out.print("Drag Coeff: ");
      System.out.printf("%.2f" , dcoeff);
      System.out.println("\n");
   
   // Print the Launch Parameters
      System.out.println("Launch Parameters ...");
      System.out.println("Launch Angle: " + langle + " deg");
      System.out.println("Launch Height: " + lheight + " cm");
      System.out.println("Launch Speed: " + lspeed + " m/s");
      System.out.println();
   
   // Print the Flight Summary
      System.out.println("Flight Summary ...");
      System.out.printf("Flight Distance: %5.2f m @ %1.4f sec \n", hpos, time);
      System.out.printf("Flight Apogee: %7.2f m @ %1.4f sec \n", apg, apgT);
      System.out.printf("Impact Speed: %8.2f m/s \n", ispd);
      System.out.printf("Impact Angle: %8.1f deg \n", iang);
      
      double g = 9.8;
      double ad = 1.162;
   // Print the Additional Info
      System.out.println();
      System.out.println("Additional Info ...");
      System.out.printf("DELTA_T: %2.4f sec \nAir Den: %1.4f kg/m^3 \nGravity: %1.4f m/s^2", deltat, ad, g);
      
   }
}