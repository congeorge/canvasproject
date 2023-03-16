package model;

public interface OperationFactory {
		String CANVAS="C";
		String LINE="L";
		String RECTANGLE="R";
		String FILL="B";
		String QUIT="Q";
		
		public static CanvasOperation getCommand(String[] inputArgs) throws  Exception {
			CanvasOperation operation=null;
			switch(inputArgs[0]){
					case CANVAS:
						operation = new DrawCanvasOperation(inputArgs);
						break;
					case LINE:
						operation = new LineOperation(inputArgs);
						break;
					case RECTANGLE:
						operation = new RectangleOperation(inputArgs);
						break;
					case FILL:
						operation = new FillOperation(inputArgs);
						break;
					case QUIT:
						operation = new QuitOperation(inputArgs);
						break;
					default:
						throw new Exception();
				}
			return operation;
		}

}
