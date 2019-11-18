package com.mygdx.theafrica;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.theafrica.CardUtils.GameCard;
import com.mygdx.theafrica.CardUtils.PlayerBase;

import java.util.ArrayList;

public class LevelManager {

    public ArrayList<Layer>Layers;

    public ArrayList<GameObject> toRemove;
    public ArrayList<GameObject> toAdd;

    public float currentTime;

    public Background bgWall;




    public LevelManager()
    {

        Layers = new ArrayList<Layer>();
        Layers.add(new Layer(Layer.LayerNames.BACKGROUND)); //0 BG
        Layers.add(new Layer(Layer.LayerNames.CARD));       //1 CARD
        Layers.add(new Layer(Layer.LayerNames.PLAYER));     //2 PLAYER
        Layers.add(new Layer(Layer.LayerNames.ICONS));      //3 ICONS
        Layers.add(new Layer(Layer.LayerNames.DEFAULT));    //4 DEFAULT


        bgWall = GOFactory.generateBGWall(-Gdx.graphics.getWidth()/2,-Gdx.graphics.getHeight()/2,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        Layers.get(0).list.add(bgWall);



        toRemove = new ArrayList<GameObject>();
        toAdd = new ArrayList<GameObject>();
        currentTime = 0f;

    }



    public void update(float delta)
    {
        currentTime += delta;


        updateLists(delta);
        removeGos();
        addGos();
        WorldController.instance.inputMgr.resetBools();
    }



    void updateLists(float delta)
    {

        for(int i = 0; i<Layers.size(); i++)
        {
            for(int j = 0; j<Layers.get(i).list.size(); j++)
            {
                Layers.get(i).list.get(j).update(delta);
            }
        }
    }

    void addGos()
    {
        for(int i = 0; i<toAdd.size(); i++)
        {
            for(Layer L:Layers)
            {
                if(L.name == toAdd.get(i).layerTag)
                {
                    L.list.add((toAdd.get(i)));
                }
            }
        }

        toAdd.clear();
    }

    void removeGos()
    {
        for(int i = toRemove.size()-1; i>=0; i--)
        {
            for(Layer L:Layers)
            {
                if(L.name == toRemove.get(i).layerTag)
                {
                    L.list.remove((toRemove.get(i)));
                }
            }
        }
        toRemove.clear();
    }


    ArrayList<GameObject> getLayerList(Layer.LayerNames _name)
    {
        ArrayList<GameObject> goList = null;
        for(Layer L:Layers)
        {
            if(L.name == _name)
            {
                goList = L.list;
            }
        }
        if(goList == null) System.out.println("Oh no the list you want is null! Check your tags or if that list exists.");
        return goList;
    }


    public void Instantiate(GameObject gameObject)//Use this to add a gameObject to the scene
    {
        toAdd.add(gameObject);
        //Center new object:
        //toAdd.get(toAdd.size()-1).x = toAdd.get(toAdd.size()-1).x-toAdd.get(toAdd.size()-1).width/2;
        //toAdd.get(toAdd.size()-1).y = toAdd.get(toAdd.size()-1).y-toAdd.get(toAdd.size()-1).width/2;

        /*new Vector2(toAdd.get(toAdd.size()-1).position.x-toAdd.get(toAdd.size()-1).width/2,
                                                         toAdd.get(toAdd.size()-1).position.y-toAdd.get(toAdd.size()-1).width/2);*/
    }

    public void Despawn(GameObject gameObject)//Use this to remove a gameObject from the scene
    {
        toRemove.add(gameObject);
    }

    void centerObject(ArrayList<GameObject> arr, int index)
    {

        if(index < arr.size())
        {
            arr.get(index).x = arr.get(index).x-arr.get(index).width/2;
            arr.get(index).y = arr.get(index).y-arr.get(index).width/2;

            //arr.get(index).position = new Vector2(arr.get(index).position.x-arr.get(index).width/2,arr.get(index).position.y-arr.get(index).width/2);
        }
        else
            System.out.println("The index is out of bounds");
    }


    public GameObject getBg()
    {
        GameObject go = null;
        for (Layer L: Layers)
        {
            if(L.name == Layer.LayerNames.BACKGROUND)
            {
                go = L.list.get(0);
            }
        }
        if(go==null) System.out.println("There is no background object");
        return go;
    }

    public Grid getGrid()
    {
        Grid go = null;
        for (Layer L: Layers)
        {
            if(L.name == Layer.LayerNames.BACKGROUND)
            {
                go = (Grid)L.list.get(1);
            }
        }
        if(go==null) System.out.println("There is no Grid object");
        return go;
    }

    public Marker getMark()
    {
        Marker go = null;
        for (Layer L: Layers)
        {
            if(L.name == Layer.LayerNames.DEFAULT)
            {
                go = (Marker)L.list.get(0);
            }
        }
        if(go==null) System.out.println("There is no Marker object");
        return go;
    }

    public Player getPlayer(int pNumber)
    {
        Player go = null;
        for (Layer L: Layers)
        {
            if(L.name == Layer.LayerNames.PLAYER)
            {
                go = (Player)L.list.get(pNumber-1);
            }
        }
        if(go==null) System.out.println("There is no Marker object");
        return go;
    }

    public GameCard getCard(int row, int column)
    {
        GameCard go = null;
        for (Layer L: Layers)
        {
            if(L.name == Layer.LayerNames.CARD)
            {

                for(int i = 0; i < L.list.size(); i++)
                {

                    if(!(L.list.get(i) instanceof PlayerBase))
                    go = (GameCard)L.list.get(i);
                    else continue;//Moui importante

                    if ((int)go.index.x == row && (int)go.index.y == column)
                    {
                        System.out.println("Card returned with row:" + go.index.x + ", column: " +go.index.y );
                        break;
                    }
                }

            }
        }
        if(go==null) System.out.println("There is no Marker object");
        return go;
    }


    public void drawDebug(ShapeRenderer shapeRender) {


    }

}
