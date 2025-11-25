import java.io.*;

public class VisitorTester
{
   public static void main(String[] args)
   {
      try
      {
         String keyword;
         if (args.length > 0) keyword = args[0];
         else
         {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter keyword: ");
            keyword = in.readLine();
            if (keyword == null) keyword = "";
         }

         DirectoryNode node = new DirectoryNode(new File(".."));
         node.accept(new FindVisitor(keyword));
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }   
}
