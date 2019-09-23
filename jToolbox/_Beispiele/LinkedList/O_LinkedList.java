package jOtto;
//%$JGUIToolboxEX$%//ID fuer Toolboxdateien

import java.util.Iterator;

/**
 * Linked-List
 * 
 * kann auch sortiert sein, wenn nur mit addSort eingefuegt wird
 * 
 * Die Klasse E muss dann das Interface Comparable implementieren
 * 
 * @author Witt
 * 
 */
public class O_LinkedList<E> implements Iterable<E>{

	O_Anker root = null;
	String test = null;

	/**
	 * Konstruktor
	 */
	public O_LinkedList() {
		root = new O_Anker();
	}

	public ListIterator iterator(){
		return new ListIterator();
	}
	
	public class ListIterator implements Iterator<E> {
		
		O_Knoten iterator = null;
		
		public ListIterator(){
			iterator=root;
		}

		@Override
		public boolean hasNext() {
			return iterator.getNextKnoten().hatDaten();
		}

		@Override
		public E next() {
			iterator=iterator.getNextKnoten();
			return iterator.getDatenelement();
		}

		@Override
		public void remove() {
			iterator.remove();
		}
		
	}
	
	class O_Knoten {

		private E datenelement;

		private O_Knoten previous = null;
		private O_Knoten next = null;

		private O_Anker anker = null;

		protected O_Knoten() {

		}

		public boolean hatDaten() {
			return (datenelement!=null);			
		}

		public boolean addDatenelement(O_Knoten previous, O_Knoten next,
				O_Anker anker, E element) {
			if (element == null)
				return false;
			new O_Knoten(previous, next, anker, element);
			return true;
		}

		private O_Knoten(O_Knoten previous, O_Knoten next, O_Anker anker,
				E element) {
			datenelement = element;
			this.anker = anker;
			// Verbindung zum Vorgaenger
			previous.setNext(this);
			this.previous = previous;
			// Verbindung zum Nachfolger
			this.next = next;
			next.setPrevious(this);
			anker.erhoeheAnzahl();
			;
		}

		protected void setNext(O_Knoten next) {
			this.next = next;
		}

		protected void setPrevious(O_Knoten previous) {
			this.previous = previous;
		}

		protected E getDatenelement() {
			return datenelement;
		}

		public void addPos(int pos, E element) {
			if (anker == this) {
				// wenn das Hinzufuegen wieder beim Anker ankommt, wird das
				// Element am Ende angehaengt
				addLast(element);
			} else {
				// Wird vor das Element mit der Positionsnummer pos gesetzt
				if (pos == 0) {
					// Position erreicht
					addDatenelement(previous, this, anker, element);
				} else {
					// Weitergabe an den naechsten mit verringerter
					// Positionsnummer
					next.addPos(pos - 1, element);
				}
			}
		}

		protected O_Knoten getNextKnoten() {
			return next;
		}

		protected O_Knoten getPrivious() {
			return previous;
		}

		public E getPos(int pos) {
			if (pos == 0) {
				return datenelement;
			} else {
				// Weitergabe an den naechsten mit verringerter
				// Positionsnummer
				return next.getPos(pos - 1);
			}
		}

		public E replace(int pos, E element) {
			if (pos == 0) {
				E temp = datenelement;
				datenelement = element;
				return temp;
			} else {
				// Weitergabe an den naechsten mit verringerter
				// Positionsnummer
				return next.replace(pos - 1, element);
			}
		}

		public E remove() {
			next.setPrevious(previous);
			previous.setNext(next);
			anker.verringerAnzahl();
			return datenelement;
		}

		
		public E remove(int pos) {
			if (pos == 0) {
				return remove();
			} else {
				return next.remove(pos - 1);
			}
		}

		public E removeElement(E element) {
			if (element == datenelement) {
				next.setPrevious(previous);
				previous.setNext(next);
				anker.verringerAnzahl();
				return datenelement;
			} else {
				return next.removeElement(element);
			}
		}

		@SuppressWarnings("unchecked")
		public void addSortIntern(E element) {
			// Hierher kommt man nur, wenn Comparable
			if (((Comparable<E>) datenelement).compareTo(element) > 0) {
				// vorher einfuegen
				addDatenelement(previous, this, anker, element);
			} else {
				next.addSortIntern(element);
			}
		}

		public boolean containsElement(E element) {
			if (datenelement.equals(element)) {
				return true;
			} else {
				return next.containsElement(element);
			}
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		public boolean searchElement(String vergleich) {
			try {
				if (((Comparable) datenelement).compareTo(vergleich) == 0) {
					return true;
				} else {
					return next.searchElement(vergleich);
				}
			} catch (Exception e) {
				return false;
			}
		}
	}

	class O_Anker extends O_Knoten {

		private int size = 0;

		O_Knoten first;
		O_Knoten last;

		// O_Knoten previous entspricht beim Anker der LETZTEN Element
		// O_Knoten next entspricht beim Anker dem ersten ELement

		public O_Anker() {
			super();
			clear();
		}

		public void clear() {
			first = this;
			last = this;
			size = 0;
		}

		public void erhoeheAnzahl() {
			size++;
		}

		public void verringerAnzahl() {
			size--;
			if (size < 0)
				size = 0;
		}

		public int size() {
			return size;
		}

		public boolean isEmpty() {
			return (size == 0);
		}

		public boolean hatDaten() {
			return false ;			
		}
		
		/**
		 * Datenelement bei Anker null == Zeichen fuer Ende der Liste
		 */
		protected E getDatenelement() {
			return null;
		}

		/**
		 * wird von O_Knoten aufgerufen setzt die Ankerspezifischen Links
		 */
		protected void setNext(O_Knoten next) {
			this.first = next;
		}

		/**
		 * wird von O_Knoten aufgerufen setzt die Ankerspezifischen Links
		 */
		protected void setPrevious(O_Knoten previous) {
			this.last = previous;
		}

		public boolean addFirst(E element) {
			addDatenelement(this, first, this, element);
			return true;
		}

		public boolean addLast(E element) {
			addDatenelement(last, this, this, element);
			return true;
		}
		
		public void addPos(int pos, E element) {
			// Zuerst werden Knoten gefragt
			// Wird ueber Knoten als letztes gefragt
			// Wenn als Anker gefragt, als Element als Letztes einfuegen
			addLast(element);
		}

		public boolean add(int pos, E element) {
			first.addPos(pos, element);
			return true;
		}

		/**
		 * Der Anker wird als letztes gefragt. Der Anker liefert immer sich
		 * selbst
		 */
		protected O_Knoten getNextKnoten() {
			return first;
		}


		public E get(int pos) {
			if ((pos < 0) || (pos >= size())) {
				return null;
			} else {
				return first.getPos(pos);
			}
		}

		/**
		 * Ersetzt das Element an pos. Liefert ersetzte Element
		 * 
		 * pos ausserhalb der Liste oliefert null
		 * 
		 * @param pos
		 * @param element
		 * @return
		 */
		public E replace(int pos, E element) {
			if ((pos < 0) || (pos >= size())) {
				return null;
			} else {
				return first.replace(pos, element);
			}

		}

		public E removeElement(E element) {
			// wird vom Knoten als letztes aufgerufen, wenn das Element in der
			// Liste nicht gefunden wird
			return null;
		}

		public E removeFirst() {
			if (size() == 0) {
				return null;
			} else if (size() == 1) {
				E temp = first.getDatenelement();
				clear();
				return temp;
			} else {
				E temp = first.getDatenelement();
				first = first.getNextKnoten();
				first.setPrevious(this);
				verringerAnzahl();
				return temp;
			}
		}

		public E removeLast() {
			if (size() == 0) {
				return null;
			} else if (size() == 1) {
				E temp = first.getDatenelement();
				clear();
				return temp;
			} else {
				E temp = last.getDatenelement();
				last = last.getPrivious();
				last.setNext(this);
				verringerAnzahl();
				return temp;
			}
		}

		public E remove() {
			return null;
		}

		
		public E remove(int pos) {
			if ((pos < 0) || (pos >= size())) {
				return remove() ;
			} else if (pos == 0) {
				return removeFirst();
			} else if (pos == size() - 1) {
				return removeLast();
			} else {
				return first.remove(pos);
			}
		}

		public E removeFirstOcurrence(E element) {
			if (isEmpty()) {
				return null;
			} else {
				return first.removeElement(element);
			}
		}

		public void addSortIntern(E element) {
			// Zuerst werden Knoten gefragt
			// Wird ueber Knoten als letztes gefragt
			// Wenn als Anker gefragt, als Element als Letztes einfuegen
			addLast(element);
		}

		public boolean addSort(E element) {
			first.addSortIntern(element);
			return true;
		}

		public boolean containsElement(E element) {
			// Zuerst werden Knoten gefragt
			// Wird ueber Knoten als letztes gefragt
			// Wenn als Anker gefragt, dann Element nicht vorhanden
			return false;
		}

		public boolean contains(E element) {
			return first.containsElement(element);
		}

		public boolean searchElement(String vergleich) {
			// Zuerst werden Knoten gefragt
			// Wird ueber Knoten als letztes gefragt
			// Wenn als Anker gefragt, dann Element nicht vorhanden
			return false;
		}

		public boolean search(String vergleich) {
			return first.searchElement(vergleich);
		}

	}

	public int size() {
		return root.size();
	}

	public boolean isEmpty() {
		return root.isEmpty();
	}

	/**
	 * Am Anfang hinzufuegen
	 * 
	 * @param element
	 * @return element
	 */
	public boolean addFirst(E element) {
		return root.addFirst(element);
	}

	/**
	 * Am Ende hinzufuegen
	 * 
	 * @param element
	 * @return element
	 */
	public boolean addLast(E element) {
		return root.addLast(element);
	}

	/**
	 * Am Ende hinzufuegen</BR> Wie addLast
	 * 
	 * @param element
	 * @return element
	 */
	public boolean add(E element) {
		return addLast(element);
	}

	/**
	 * An Position pos hinzufuegen</BR>
	 * 
	 * @param element
	 *            , wird auf jeden Fall hinzugefuegt.
	 * @return element
	 * 
	 */
	public boolean add(int pos, E element) {
		return root.add(pos, element);
	}

	public E get(int pos) {
		return root.get(pos);
	}

	public void clear() {
		root.clear();
	}

	/**
	 * Ersetzt das Element an pos. Liefert ersetzte Element
	 * 
	 * pos ausserhalb der Liste oliefert null
	 * 
	 * @param pos
	 * @param element
	 * @return element
	 */
	public E replace(int pos, E element) {
		return root.replace(pos, element);
	}

	/**
	 * Anfang entfernen
	 * 
	 * @return element
	 */
	public E removeFirst() {
		return root.removeFirst();
	}

	/**
	 * Am Ende entfernen
	 * 
	 * @return element
	 */
	public E removeLast() {
		return root.removeLast();
	}

	/**
	 * element entfernen
	 * 
	 * @param element
	 * @return element 
	 */
	public E removeFirstOcurrence(E element) {
		return root.removeFirstOcurrence(element);
	}

	/**
	 * Position pos entfernen</BR>
	 * 
	 * @return element
	 * 
	 */
	public E remove(int pos) {
		return root.remove(pos);
	}
	/**
	 * Position pos entfernen</BR>
	 * 
	 * @return element
	 * 
	 */
	
	public E remove(E element) {
		return removeFirstOcurrence(element);
	}

	public boolean addSort(E element) {
		if (element instanceof Comparable<?>) {
			return root.addSort(element);
		} else {
			return false;
		}
	}

	public boolean contains(E element) {
		return root.contains(element);
	}

	public boolean search(String vergleich) {
		return root.search(vergleich);
	}

}
