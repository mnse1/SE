import java.io.*;

public class FindVisitor implements FileSystemVisitor
{
   public FindVisitor(String keyword)
   {
      this.keyword = keyword;
   }

   public void visitFileNode(FileNode node)
   {
      if (containsKeyword(node.getFile().getName()))
      {
         for (int i = 0; i < level; i++) System.out.print(" ");
         System.out.println(node.getFile().getName());
      }
   }

   public void visitDirectoryNode(DirectoryNode node)
   {
      if (containsKeyword(node.getDirectory().getName()))
      {
         for (int i = 0; i < level; i++) System.out.print(" ");
         System.out.println(node.getDirectory().getName());
      }
      level++;
      for (FileSystemNode c : node.getChildren())
         c.accept(this);
      level--;
   }

   private boolean containsKeyword(String name)
   {
      return name.indexOf(keyword) >= 0;
   }

   private int level = 0;
   private String keyword;
}
