class jiou {
    public double myPow(double x, int n) {
        long N=n;
        return N>=0?quick(x,N):1/quick(x,-N);
    }
    public double quick(double x,long N){
        if(N==0){
            return 1;
        }
        double y=quick(x,N/2);
        return N%2==0?y*y:y*y*x;
    }
}