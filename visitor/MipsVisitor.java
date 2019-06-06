package visitor;
import syntaxtree.*;
import java.util.*;



public class MipsVisitor extends GJNoArguDepthFirst<String> {

	public String alloc = "";
	

	/*
	 * 	.text
		.globl _halloc
	_halloc: li $v0, 9
		syscall
		j $ra
		
		.text
		.globl _print
	_print: li $v0, 1
		syscall
		la $a0, newl
		li $v0, 4
		syscall
		j $ra

		.data
		.align 0
	newl: .asciiz "\n"
		.data
		.align 0
	str_er: .asciiz " ERROR: abnormal termination\n" 
	 */
	void genalloc() {
		alloc += "\t.text\n\t.globl_halloc\n";
		alloc += "_halloc:\n\tli $v0, 9\n\tsyscall\n\tj $ra\n\n";
		alloc += "\t.text\n\t.globl_print\n";
		alloc += "_print:\n\tli $v0, 1\n\tsyscall\n\tla $a0, newl\n\tli $v0, 4\n\tsyscall\n\tj $ra\n\n";
		alloc += "\t.data\n\t.align 0\n";
		alloc += "newl:\n\t.asciiz \"\\n\"\n\t.data\n\t.align 0\n";
		alloc += "str_er:\n\t.asciiz \" ERROR: abnormal termination\\n\"";
	}


	/*
	 * Represent a grammar list, e.g. (A)+
	 * 
	 */
	public String visit(NodeList n) {
		String _ret = null;
		for (Enumeration<Node> e = n.elements(); e.hasMoreElements();)
			e.nextElement().accept(this);
		return _ret;
	}
	

	/*
	 * Represent a optional grammar list, e.g. (A)
	 * 
	 */
	public String visit(NodeListOptional n) {
		if (n.present()) {
		String _ret = null;
		for (Enumeration<Node> e = n.elements(); e.hasMoreElements();)
			e.nextElement().accept(this);
			return _ret;
		}
		else
			return null;
	}
	

	/*
	 * Represent a grammar optional node, e.g. (A)?
	 * 
	 */
	public String visit(NodeOptional n) {
		if (n.present())
			return n.node.accept(this);
		else
			return null;
	}
	
	
	public String visit(NodeSequence n) {
		String _ret = null;
		for (Enumeration<Node> e = n.elements(); e.hasMoreElements();)
			e.nextElement().accept(this);
		return _ret;
	}
	


	public String visit(NodeToken n) { return n.tokenImage; }
	
	//
	// User-generated visitor methods below
	//
	
	/**
	 * f0 -> "MAIN"
	* f1 -> "["
	* f2 -> IntegerLiteral()
	* f3 -> "]"
	* f4 -> "["
	* f5 -> IntegerLiteral()
	* f6 -> "]"
	* f7 -> "["
	* f8 -> IntegerLiteral()
	* f9 -> "]"
	* f10 -> StmtList()
	* f11 -> "END"
	* f12 -> ( Procedure() )*
	* f13 -> <EOF>
	*/
	public R visit(Goal n) {
		R _ret=null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		n.f3.accept(this);
		n.f4.accept(this);
		n.f5.accept(this);
		n.f6.accept(this);
		n.f7.accept(this);
		n.f8.accept(this);
		n.f9.accept(this);
		n.f10.accept(this);
		n.f11.accept(this);
		n.f12.accept(this);
		n.f13.accept(this);
		return _ret;
	}
	
	/**
	 * f0 -> ( ( Label() )? Stmt() )*
	*/
	public R visit(StmtList n) {
		R _ret=null;
		n.f0.accept(this);
		return _ret;
	}
	
	/**
	 * f0 -> Label()
	* f1 -> "["
	* f2 -> IntegerLiteral()
	* f3 -> "]"
	* f4 -> "["
	* f5 -> IntegerLiteral()
	* f6 -> "]"
	* f7 -> "["
	* f8 -> IntegerLiteral()
	* f9 -> "]"
	* f10 -> StmtList()
	* f11 -> "END"
	*/
	public R visit(Procedure n) {
		R _ret=null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		n.f3.accept(this);
		n.f4.accept(this);
		n.f5.accept(this);
		n.f6.accept(this);
		n.f7.accept(this);
		n.f8.accept(this);
		n.f9.accept(this);
		n.f10.accept(this);
		n.f11.accept(this);
		return _ret;
	}
	
	/**
	 * f0 -> NoOpStmt()
	*       | ErrorStmt()
	*       | CJumpStmt()
	*       | JumpStmt()
	*       | HStoreStmt()
	*       | HLoadStmt()
	*       | MoveStmt()
	*       | PrintStmt()
	*       | ALoadStmt()
	*       | AStoreStmt()
	*       | PassArgStmt()
	*       | CallStmt()
	*/
	public String visit(Stmt n) {
		return n.f0.accept(this);
	}
	
	/**
	 * f0 -> "NOOP"
	*/
	public String visit(NoOpStmt n) {
		return "nop";
	}
	
	// /**
	//  * f0 -> "ERROR"
	// */
	// public R visit(ErrorStmt n) {
	// 	R _ret=null;
	// 	n.f0.accept(this);
	// 	return _ret;
	// }
	
	/**
	 * f0 -> "CJUMP"
	* f1 -> Reg()
	* f2 -> Label()
	*/
	public String visit(CJumpStmt n) {
		return "beqz " + n.f1.accept(this) + " " + n.f2.accept(this);
	}
	
	/**
	 * f0 -> "JUMP"
	* f1 -> Label()
	*/
	public String visit(JumpStmt n) {
		return "b " + n.f1.accept(this);
	}
	
	/**
	 * f0 -> "HSTORE"
	* f1 -> Reg()
	* f2 -> IntegerLiteral()
	* f3 -> Reg()
	*/
	public R visit(HStoreStmt n) {
		R _ret=null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		n.f3.accept(this);
		return _ret;
	}
	
	/**
	 * f0 -> "HLOAD"
	* f1 -> Reg()
	* f2 -> Reg()
	* f3 -> IntegerLiteral()
	*/
	public R visit(HLoadStmt n) {
		R _ret=null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		n.f3.accept(this);
		return _ret;
	}
	
	/**
	 * f0 -> "MOVE"
	* f1 -> Reg()
	* f2 -> Exp()
	*/
	public String visit(MoveStmt n) {
		int c = n.f2.f0.which;
		if (c == 1) {	// Exp -> Binop
			String []buf = n.f2.accept(this).split(" ");
			return buf[0] + " " + n.f1.accept(this) + buf[1];
		} else {
			return "" // !!!
		}
	}
	
	/**
	 * f0 -> "PRINT"
	* f1 -> SimpleExp()
	*/
	public R visit(PrintStmt n) {
		R _ret=null;
		n.f0.accept(this);
		n.f1.accept(this);
		return _ret;
	}
	
	/**
	 * f0 -> "ALOAD"
	* f1 -> Reg()
	* f2 -> SpilledArg()
	*/
	public R visit(ALoadStmt n) {
		R _ret=null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		return _ret;
	}
	
	/**
	 * f0 -> "ASTORE"
	* f1 -> SpilledArg()
	* f2 -> Reg()
	*/
	public R visit(AStoreStmt n) {
		R _ret=null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		return _ret;
	}
	
	/**
	 * f0 -> "PASSARG"
	* f1 -> IntegerLiteral()
	* f2 -> Reg()
	*/
	public R visit(PassArgStmt n) {
		R _ret=null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		return _ret;
	}
	
	/**
	 * f0 -> "CALL"
	* f1 -> SimpleExp()
	*/
	public R visit(CallStmt n) {
		R _ret=null;
		n.f0.accept(this);
		n.f1.accept(this);
		return _ret;
	}
	
	/**
	 * f0 -> HAllocate()
	*       | BinOp()
	*       | SimpleExp()
	*/
	public R visit(Exp n) {
		R _ret=null;
		n.f0.accept(this);
		return _ret;
	}
	
	/**
	 * f0 -> "HALLOCATE"
	* f1 -> SimpleExp()
	*/
	public R visit(HAllocate n) {
		R _ret=null;
		n.f0.accept(this);
		n.f1.accept(this);
		return _ret;
	}
	
	/**
	 * f0 -> Operator()
	* f1 -> Reg()
	* f2 -> SimpleExp()
	*/
	// "slt (),$s1,$t0"
	public String visit(BinOp n) {
		return n.f0.accept(this) + " ," + n.f1.accept(this) + n.f2.accept(this);
	}
	
	/**
	 * f0 -> "LT"
	*       | "PLUS"
	*       | "MINUS"
	*       | "TIMES"
	*/
	public String visit(Operator n) {
		int c = n.f0.which;
		String []buf = { "slt", "add", "sub", "mul" };
		return buf[c];
	}
	
	/**
	 * f0 -> "SPILLEDARG"
	* f1 -> IntegerLiteral()
	*/
	public R visit(SpilledArg n) {
		R _ret=null;
		n.f0.accept(this);
		n.f1.accept(this);
		return _ret;
	}
	
	/**
	 * f0 -> Reg()
	*       | IntegerLiteral()
	*       | Label()
	*/
	public String visit(SimpleExp n) {
		return n.f0.accept(this);
	}
	
	/**
	 * f0 -> "a0"
	*       | "a1"
	*       | "a2"
	*       | "a3"
	*       | "t0"
	*       | "t1"
	*       | "t2"
	*       | "t3"
	*       | "t4"
	*       | "t5"
	*       | "t6"
	*       | "t7"
	*       | "s0"
	*       | "s1"
	*       | "s2"
	*       | "s3"
	*       | "s4"
	*       | "s5"
	*       | "s6"
	*       | "s7"
	*       | "t8"
	*       | "t9"
	*       | "v0"
	*       | "v1"
	*/
	public String visit(Reg n) {
		return "$" + n.f0.accept(this);
	}
	
	/**
	 * f0 -> <INTEGER_LITERAL>
	*/
	public String visit(IntegerLiteral n) {
		return n.f0.tokenImage;
	}
	
	/**
	 * f0 -> <IDENTIFIER>
	*/
	public String visit(Label n) {
		return n.f0.tokenImage;
	}
	
}
