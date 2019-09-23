//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Vector;

public class OXML_Element {
    String elementbezeichnung;
    String inhalt;
    Vector<OXML_Element> unterelemente;

    boolean bKommentar = false;

    protected OXML_Element() {
        unterelemente = new Vector<OXML_Element>();
    }

    public static OXML_Element getElement(String elementbezeichnung) {
        OXML_Element element = new OXML_Element();
        element.elementbezeichnung = elementbezeichnung;
        element.bKommentar = false;
        return element;
    }

    public OXML_Element addElement(String elementbezeichnung, OXML_Element parent) {
        if (parent == this) {
            OXML_Element tmp = new OXML_Element();
            tmp.elementbezeichnung = elementbezeichnung;
            tmp.bKommentar = false;
            unterelemente.add(tmp);
            return tmp;
        } else {
            for (OXML_Element aktElement : unterelemente) {
                OXML_Element tmp = aktElement.addElement(elementbezeichnung, parent);
                if (tmp != null) {
                    return tmp;
                }
            }
            return null;
        }
    }

    public boolean addText(OXML_Element element, String inhalt) {
        if (element == this) {
            this.inhalt = inhalt;
            bKommentar = false;
            return true;
        } else {
            for (OXML_Element aktElement : unterelemente) {
                boolean erg = aktElement.addText(element, inhalt);
                if (erg) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public boolean addBoolean(OXML_Element element, boolean inhalt) {
        return addText( element, ""+inhalt);
    }

    public boolean addInt(OXML_Element element, int inhalt) {
        return addText( element, ""+inhalt);
    }
    
    public boolean addLong(OXML_Element element, long inhalt) {
        return addText( element, ""+inhalt);
    }
    
    public boolean addDouble(OXML_Element element, double inhalt) {
        return addText( element, ""+inhalt);
    }

    
    public boolean addComment(OXML_Element parent, String text) {
        if (parent == this) {
            OXML_Element tmp = new OXML_Element();
            tmp.elementbezeichnung = text;
            tmp.bKommentar = true;
            unterelemente.add(tmp);
            return true;
        } else {
            for (OXML_Element aktElement : unterelemente) {
                boolean erg = aktElement.addComment(parent, inhalt);
                if (erg) {
                    return true;
                }
            }
            return false;
        }
    }

    public String getBezeichnung() {
        return elementbezeichnung;
    }

    public String getInhalt() {
        return inhalt;
    }

    public String getInhaltTrim() {
        if (inhalt == null) {
            return "";
        } else {
            return inhalt.trim();
        }
    }

    public int getInhaltInt(int fehlerwert) {
        int wert = fehlerwert;
        try {
            wert = Integer.parseInt(inhalt);
        } catch (Exception e) {
            wert = fehlerwert;
        }
        return wert;
    }

    public long getLong( long fehlerwert ){
        long wert = fehlerwert;
        try {
            wert = Long.parseLong(inhalt);
        } catch (Exception e) {
            wert = fehlerwert ;
        }
        return wert;
    }

    public double getDouble(double fehlerwert){
        double wert = fehlerwert;
        try {
            wert = Double.parseDouble(inhalt);
        } catch (Exception e) {
            wert = fehlerwert ;
        }
        return wert ;
    }

    public boolean getLong( boolean fehlerwert ){
        boolean wert = fehlerwert;
        try {
            wert = Boolean.parseBoolean(inhalt);
        } catch (Exception e) {
            wert = fehlerwert ;
        }
        return wert ;
    }
   
    public void schreibeElement(BufferedWriter bfw, String ein) {
        try {
            if (bKommentar) {
                bfw.write(ein + "<!-- " + elementbezeichnung + " -->");
                bfw.newLine();
            } else {
                bfw.write(ein + "<" + elementbezeichnung + ">");
                bfw.write(OXML.codeString(inhalt));
                if (unterelemente.isEmpty()) {
                    bfw.write("</" + elementbezeichnung + ">");
                    bfw.newLine();
                } else {
                    bfw.newLine();
                    for (OXML_Element aktElement : unterelemente) {
                        aktElement.schreibeElement(bfw, ein + OXML.einruecken);
                    }
                    bfw.write(ein + "</" + elementbezeichnung + ">");
                    bfw.newLine();
                }
            }
        } catch (IOException e) {
        }
    }

    public OXML_Element getElement(OXML_Element parent, String elementbezeichnung) {
        if (parent == this) {
            for (OXML_Element aktElement : unterelemente) {
                if (aktElement.getBezeichnung().equalsIgnoreCase(elementbezeichnung)) {
                    return aktElement;
                }
            }
            return null;
        } else {
            for (OXML_Element aktElement : unterelemente) {
                OXML_Element element = aktElement.getElement(parent, elementbezeichnung);
                if (element != null) {
                    return element;
                }
            }
            return null;
        }
    }

    public Vector<OXML_Element> getElements(OXML_Element parent, String elementbezeichnung2) {
        if (parent == this) {
            return unterelemente;
        } else {
            for (OXML_Element aktElement : unterelemente) {
                Vector<OXML_Element> elemente = aktElement.getElements(parent, elementbezeichnung);
                if (elemente != null) {
                    return elemente;
                }
            }
            return null;
        }
    }

}
