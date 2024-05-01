package com.puterblade.markdowntools;

import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.YailList;
import io.github.furstenheim.CopyDown;
import com.youbenzi.mdtool.tool.MDTool;


public class Markdowntools extends AndroidNonvisibleComponent {

  public Markdowntools(ComponentContainer container) {
    super(container.$form());
  }

  @SimpleFunction(description = "Converts HTML to Markdown and returns it as string.")
  public String ConvertHTMLToMarkdown(String html) {
    CopyDown converter = new CopyDown();
    String markdown = converter.convert(html);
    return markdown;
  }

  @SimpleFunction(description = "Converts Markdown to HTML and returns it as string.")
  public String ConvertMarkdownToHTML(String markdown) {
    String convhtml = MDTool.markdown2Html(markdown);
    return convhtml;
  }

  @SimpleFunction(description = "Creates markdown heading from text.")
  public String CreateHeading(int headingLevel, String text) {
    String headinghash = "";
    for (int i = 1; i <= headingLevel; i++) {
      headinghash = headinghash + "#";
    }
    return headinghash + " " + text;
  }

  @SimpleFunction(description = "Creates markdown bold text from plain text.")
  public String CreateBoldText(String text) {
    return "**" + text + "**";
  }

  @SimpleFunction(description = "Creates markdown italic text from plain text.")
  public String CreateItalicText(String text) {
    return "_" + text + "_";
  }

  @SimpleFunction(description = "Creates markdown blockquote from text.")
  public String CreateBlockquote(String text) {
    return "> " + text;
  }

  @SimpleFunction(description = "Creates markdown ordered list from text.")
  public String CreateOrderedList(YailList listItems, boolean htmlLineBreak) {
    String fl = "";
    int s = 0;
    for (final Object o : listItems.toArray()) {
      try {
        s += 1;
        String tc = o.toString();
        if (htmlLineBreak) {
          StringBuilder sb = new StringBuilder();
          fl += Integer.toString(s) + ". " + tc + "<br>";
          sb.append(fl);
          sb.append("<br>");
        } else {
          fl += Integer.toString(s) + ". " + tc + "\n";
        }
      } catch (Exception e) {
        throw new YailRuntimeError(e.toString(), "Exception");
      }
    }
    return fl;
  }

  @SimpleFunction(description = "Creates markdown unordered list from text.")
  public String CreateUnorderedList(YailList listItems, boolean htmlLineBreak) {
    String fl = "";
    for (final Object o : listItems.toArray()) {
      try {
        String tc = o.toString();
        fl += "- " + tc;
        if (htmlLineBreak) {
          StringBuilder sb = new StringBuilder();
          fl += "- " + tc + "<br>";
          sb.append(fl);
          sb.append("<br>");
        } else {
          fl += "- " + tc + "\n";
        }
      } catch (Exception e) {
        throw new YailRuntimeError(e.toString(), "Exception");
      }
    }
    return fl;
  }

  @SimpleFunction(description = "Creates markdown code block from text.")
  public String CreateCodeBlock(String text) {
    return "`" + text + "`";
  }

  @SimpleFunction(description = "Creates markdown horizontal rule.")
  public String CreateHorizontalRule() {
    return "---";
  }

  @SimpleFunction(description = "Creates markdown hyperlink from URL and alt text. Alt text is optional.")
  public String CreateHyperlink(String linkUrl, String linkAltText) {
    if (linkAltText.isEmpty()) {
      return "[" + linkUrl + "](" + linkUrl + ")";
    } else {
      return "[" + linkAltText + "](" + linkUrl + ")";
    }
  }

  @SimpleFunction(description = "Creates markdown image from image link and image alt text.")
  public String CreateImage(String imgUrl, String imgAltText) {
    if (imgAltText.isEmpty()) {
      return "![image](" + imgUrl + ")";
    } else {
      return "![" + imgAltText + "](" + imgUrl + ")";
    }
  }

  @SimpleFunction(description = "Creates markdown strikethrough from text.")
  public String CreateStrikethrough(String text) {
    return "~~" + text + "~~";
  }
}
