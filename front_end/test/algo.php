<?php
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);


$current_mix = [1,2,3];
$new_mix = [1,2,3];

var_dump(array_diff($current_mix,$new_mix ));

die();

$items = [];

$items[1]['name']   = 'T-shirt';
$items[1]['type']   = 'T';
$items[1]['single'] = 0;

$items[2]['name']   = 'Frock';
$items[2]['type']   = 'TM';
$items[2]['single'] = 1;

$items[3]['name']   = 'Saree';
$items[3]['type']   = 'MM';
$items[3]['single'] = 0;

$items[4]['name']   = 'Shoe';
$items[4]['type']   = 'B';
$items[4]['single'] = 0;

$items[5]['name']   = 'Jean';
$items[5]['type']   = 'M';
$items[5]['single'] = 0;

$items[6]['name']   = 'Flip-Flop';
$items[6]['type']   = 'B';
$items[6]['single'] = 0;


$items[7]['name']   = 'Jacket';
$items[7]['type']   = 'TT';
$items[7]['single'] = 0;


$tops    = [];
$mids    = [];
$bottoms = [];
$topTops = [];
$midMids = [];


function getVariations($total_items)
{
    $pieces = 3;


    $total_items = count($total_items);


    return pow($pieces, $total_items);
}



/*
 * @todo store generated mix and keep it globally and compare it with newly generated mix. If matched re-generate a mix.
 *
 */
function getStyle($items, &$tops, &$mids, &$bottoms, &$topTops, &$midMids)
{
    //separate item types
    getCorrectItems($items, $tops, $mids, $bottoms, $topTops, $midMids);

//    $top     =$items[3]; //  $tops[array_rand($tops)];
//    $mid     =$items[7]; //$mids[array_rand($mids)];
//    $bottom  =$items[4]; //$bottoms[array_rand($bottoms)];
//    $topTops =$items[7]; //$topTops[array_rand($topTops)];
//    $midMids =$items[3]; //$midMids[array_rand($midMids)];


    $top     = $tops[array_rand($tops)];
    $mid     = $mids[array_rand($mids)];
    $bottom  = $bottoms[array_rand($bottoms)];
    $topTops = $topTops[array_rand($topTops)];
    $midMids = $midMids[array_rand($midMids)];


    //Frock example
    if ($top['type'] == 'TM') {
        $mid = $top;
    }

    //Jacket example
    if ($top['type'] == 'TT') {
        $mid = $midMids;
    }

    //Saree example
    if ($top['type'] == 'MM') {
        $mid = $top;
        $top = $topTops;
    }



    return [$top, $mid, $bottom];

}

function getCorrectItems($items, &$tops, &$mids, &$bottoms, &$topTops, &$midMids)
{
    foreach ($items as $item) {
        switch ($item['type']) {
            case 'T' :
                $tops[] = $item;
                break;

            case 'M' :
                $mids[] = $item;
                break;

            case 'B' :
                $bottoms[] = $item;
                break;

            case 'TM' :
                $tops[] = $item;
                break;

            case 'TT' :
                $topTops[] = $item;
                $tops[]    = $item;
                break;

            case 'MM' :
                $midMids[] = $item;
                $tops[]    = $item;
                break;
        }

    }
}


//execution

//echo getVariations($items);

var_dump(getStyle($items, $tops, $mids, $bottoms, $topTops, $midMids));


echo "tops";
var_dump($tops);

echo "mids";
var_dump($mids);

echo "bottom";
var_dump($bottoms);

echo "topTops";
var_dump($topTops);

echo "midMids";
var_dump($midMids);
