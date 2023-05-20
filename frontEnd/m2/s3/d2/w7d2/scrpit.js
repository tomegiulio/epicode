class person{
    constructor(_name, _age){
        this.name=_name;
        this.age=_age
    }
    confronto(other){
        if(this.age>other.age){
            console.log(`${this.name} e piu vecchio di ${other.name} `)
        }else {console.log(`${this.name} e piu giovane di ${other.name} `)}
    }
    
    }
    

    const p1=new person("Giulio",28);
    const p2=new person("Giorgio",22);
    const p3=new person("Giovanni",23);
    
    p1.confronto(p2);
    p2.confronto(p3);
    p3.confronto(p1);